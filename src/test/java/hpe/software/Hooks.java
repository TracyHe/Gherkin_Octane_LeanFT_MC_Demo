package hpe.software;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.web.Browser;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import hpe.software.leanft.CucumberBrowserFactory;

public class Hooks {

	Browser browser = null;
	CucumberBrowserFactory cucumnerBrowserFactory = null;
	
	@Before
	public void before() {
		cucumnerBrowserFactory = new CucumberBrowserFactory();
	}
	
	@After
	public void after() throws GeneralLeanFtException {
		cucumnerBrowserFactory.close();
	}
}
