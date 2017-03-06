package hpe.software;

import java.net.URI;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.hp.lft.report.Reporter;
import com.hp.lft.sdk.ModifiableSDKConfiguration;
import com.hp.lft.sdk.SDK;
import com.hp.lft.sdk.mobile.Device;
import com.hp.lft.sdk.mobile.DeviceDescription;
import com.hp.lft.sdk.mobile.MobileLab;
import com.hp.lft.sdk.web.Browser;
import com.hp.lft.sdk.web.BrowserFactory;
import com.hp.lft.sdk.web.BrowserType;
import com.hp.lft.sdk.web.Button;
import com.hp.lft.sdk.web.ButtonDescription;
import com.hp.lft.sdk.web.EditField;
import com.hp.lft.sdk.web.EditFieldDescription;
import com.hp.lft.sdk.web.Image;
import com.hp.lft.sdk.web.ImageDescription;
import com.hp.lft.sdk.web.Link;
import com.hp.lft.sdk.web.LinkDescription;

public class SelfContainingSogetiTest {

	Browser browser = null;
	Device device = null;

	@Before
	public void before() throws Exception {

		ModifiableSDKConfiguration config = new ModifiableSDKConfiguration();
		config.setServerAddress(new URI("ws://localhost:5095"));
		SDK.init(config);
		Reporter.init();

		DeviceDescription deviceDescription = new DeviceDescription();
		deviceDescription.setModel("iPad");
		device = MobileLab.lockDevice(deviceDescription);
		browser = BrowserFactory.launch(BrowserType.SAFARI, device);
	}

	@After
	public void after() throws Exception {
		if (browser != null)
			browser.close();

		if (device != null)
			device.unlock();
	}

	@Test
	public void theTest() throws Exception {

		browser.navigate("http://google.com");

		browser.describe(EditField.class,
				new EditFieldDescription.Builder().type("text").tagName("INPUT").name("q").build())
				.setValue("Sogeti UK");

		browser.describe(Button.class,
				new ButtonDescription.Builder().buttonType("submit").tagName("BUTTON").index(0).build()).click();

		browser.describe(Link.class,
				new LinkDescription.Builder().href("https://www.uk.sogeti.com/").tagName("A").build()).click();

		boolean isSogetiImageVisible = browser.describe(Image.class, new ImageDescription.Builder().alt("Sogeti Logo")
				.type(com.hp.lft.sdk.web.ImageType.LINK).tagName("IMG").index(1).build()).isVisible();

		Assert.assertTrue(isSogetiImageVisible);
	}

}
