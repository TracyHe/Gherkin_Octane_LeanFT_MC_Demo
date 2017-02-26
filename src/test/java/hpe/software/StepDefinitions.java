package hpe.software;

import org.junit.Assert;

import com.hp.lft.sdk.web.*;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import hpe.software.leanft.CucumberBrowserFactory;

public class StepDefinitions {

	Browser browser = null;

	@Given("^User has opened google homepage$")
	public void user_has_opened_google_homepage() throws Throwable {
		browser = new CucumberBrowserFactory().getBrowser();
		browser.navigate("http://google.com");
	}

	@When("^searching for Sogeti$")
	public void searching_for_Sogeti() throws Throwable {
		browser.describe(EditField.class,
				new EditFieldDescription.Builder().type("text").tagName("INPUT").name("q").build())
				.setValue("Sogeti UK");

		browser.describe(Button.class,
				new ButtonDescription.Builder().buttonType("submit").tagName("BUTTON").index(0).build()).click();
	}

	@When("^clicking the Sogeti link$")
	public void clicking_the_Sogeti_link() throws Throwable {
		browser.describe(Link.class, new LinkDescription.Builder()
				.href("https://www.uk.sogeti.com/").tagName("A").build()).click();
	}

	@Then("^The Sogeti homepage is displayed$")
	public void the_Sogeti_homepage_is_displayed() throws Throwable {
		boolean isSogetiImageVisible = browser.describe(Image.class, new ImageDescription.Builder().alt("Sogeti Logo")
				.type(com.hp.lft.sdk.web.ImageType.LINK).tagName("IMG").index(1).build()).isVisible();

		Assert.assertTrue(isSogetiImageVisible);
	}
}