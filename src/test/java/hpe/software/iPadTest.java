package hpe.software;

import com.hpe.alm.octane.OctaneCucumber;
//import cucumber.api.junit.Cucumber;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import unittesting.*;
import cucumber.api.CucumberOptions;
import hpe.software.leanft.CucumberBrowserFactory;

import org.junit.runner.RunWith;


@RunWith(OctaneCucumber.class)
@CucumberOptions(
		monochrome = true,
		plugin={"junit:target/cucumber-testresults/iPadJunitResult.xml"},
		features="src/test/resources/hpe/software",
		tags ={"@iPad"}) 
public class iPadTest extends UnitTestClassBase {

    public iPadTest() {
        //Change this constructor to private if you supply your own public constructor
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    	new CucumberBrowserFactory("iPad");
        instance = new iPadTest();
        globalSetup(iPadTest.class);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        globalTearDown();
        new CucumberBrowserFactory().quit();
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

}