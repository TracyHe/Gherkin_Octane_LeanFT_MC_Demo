package hpe.software.leanft;

import com.hp.lft.sdk.Environment;
import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.mobile.Device;
import com.hp.lft.sdk.mobile.MobileLab;
import com.hp.lft.sdk.web.Browser;
import com.hp.lft.sdk.web.BrowserFactory;
import com.hp.lft.sdk.web.BrowserType;

public class CucumberBrowserFactory {

	private static String environment = null;

	public CucumberBrowserFactory() {

	}

	public CucumberBrowserFactory(String environment) throws IllegalArgumentException {
		if (CucumberBrowserFactory.environment != null) {
			throw new IllegalArgumentException("This environment has already been configured");
		}
		CucumberBrowserFactory.environment = environment;
	}

	public String getEnvironment() {
		return environment;
	}

	private static Environment device;
	private static Browser browser;

	public Browser getBrowser() throws GeneralLeanFtException {
		if (browser != null)
			return browser;

		Environment device = getDevice(this.getEnvironment());

		if ("iPad".equalsIgnoreCase(getEnvironment())) {
			browser = BrowserFactory.launch(BrowserType.SAFARI, device);
		}

		if ("Chrome".equalsIgnoreCase(getEnvironment())) {
			browser = BrowserFactory.launch(BrowserType.CHROME, device);
		}

		if ("Firefox".equalsIgnoreCase(getEnvironment())) {
			browser = BrowserFactory.launch(BrowserType.FIREFOX, device);
		}

		if ("IE".equalsIgnoreCase(getEnvironment())) {
			browser = BrowserFactory.launch(BrowserType.INTERNET_EXPLORER, device);
		}
		return browser;
	}

	private Environment getDevice(String environment) throws GeneralLeanFtException {
		if (device != null)
			return device;

		if ("iPad".equalsIgnoreCase(environment)) {
			String iPadSerial = "eec2605aa907525438d677e4929d53d41305b466";
			device = MobileLab.lockDeviceById(iPadSerial);
		}
		return device;
	}

	public void close() throws GeneralLeanFtException {

		if (browser != null) {
			browser.close();
			browser = null;
		}

		if (device != null) {

			if (device instanceof Device) {
				((Device) device).unlock();
			}
			device = null;
		}
	}

	public void quit() {
		environment = null;
	}
}
