package AppHooks;

import java.io.File;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;

import com.qa.factory.DriverFactory;
import com.utils.ConfigReader;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ApplicationHooks {

	private DriverFactory driverFactory;
	private WebDriver driver;
	//private WebDriver driver = new ChromeDriver();
	
	
	private ConfigReader configReader;
	Properties prop;

	@Before(order = 0)
	//@BeforeClass(Priority=0)
	public void getProperty() {
		configReader = new ConfigReader();
		prop = configReader.init_prop();
	}

	@Before(order = 1)
	public void launchBrowser() {
		driverFactory = new DriverFactory();
		String browserName = prop.getProperty("browser");  //browserName=Chrome;
		
		
		driver = driverFactory.init_driver(browserName);
		
	}

	@After(order = 0)
	public void quitBrowser() {
		driver.quit();
	}

	@After(order = 1)
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()==true) {
			// take screenshot:
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			
			//File n=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			scenario.attach(sourcePath, "image/png", screenshotName);

		}
	}

}
