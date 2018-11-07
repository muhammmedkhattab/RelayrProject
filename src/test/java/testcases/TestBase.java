package testcases;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import data.DataDriven;
import utilities.Helper;

public class TestBase {

	public static WebDriver driver;
	String homePageURL = DataDriven.getCellData("GeneralTestData", "HomePageProdURL", 1);

	public static FirefoxOptions firefoxOption() {
		FirefoxOptions option = new FirefoxOptions();
		option.addPreference("browser.download.folderList", 2);
		// option.addPreference("browser.download.dir", downloadPath);
		option.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
		option.addPreference("browser.download.manager.showWhenStarting", false);
		option.addPreference("pdfjs.disabled", true);
		return option;
	}

	public static ChromeOptions chromeOption() {
		ChromeOptions options = new ChromeOptions();
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default.content_settings.popups", 0);
		// chromePrefs.put("download.default_directory", downloadPath);
		options.setExperimentalOption("prefs", chromePrefs);
		options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		return options;

	}
	public static ChromeOptions chromeHeadless() {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		options.addArguments("--window-size=1920,1080");
		return options;
		
	} 

	@BeforeClass
	@Parameters({ "browser" })
	public void startDriver(@Optional("Chrome") String browserName) {

		if (browserName.equalsIgnoreCase("Chrome")) {

			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
			driver = new ChromeDriver(chromeOption());
		}

		else if (browserName.equalsIgnoreCase("firefox")) {

			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/drivers/geckodriver.exe");
			driver = new FirefoxDriver(firefoxOption());
		}

		else if (browserName.equalsIgnoreCase("ie")) {

			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();

		} else if (browserName.equalsIgnoreCase("chrome-headless")) {

			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
			driver = new ChromeDriver(chromeHeadless());

		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
		driver.navigate().to(homePageURL);
		//
	}

	@AfterClass
	public void exitDriver() throws InterruptedException {

		driver.quit();

	}

	@AfterSuite
	public void generateReport() throws IOException {

		Helper.openReport();
	}

	@AfterMethod
	public void screenShotOnFailure(ITestResult result) throws InterruptedException {

		if (ITestResult.FAILURE == result.getStatus()) {
			// Take a screenshot when TC failed and add it in the the Screenshots Folder
			Thread.sleep(500);
			Helper.captureScreenShot(driver, result.getName().concat(" [Failed]"));
			Thread.sleep(500);

			System.out.println("Taking Screenshot....");

		}
		// Take a screenshot when TC Passed and add it in the the Screenshots Folder
		else if (ITestResult.SUCCESS == result.getStatus()) {
			Thread.sleep(500);
			Helper.captureScreenShot(driver, result.getName().concat(" [Passed]"));
			Thread.sleep(500);
			System.out.println("Passed!");
			System.out.println("Taking Screenshot....");

		}
	}

}
