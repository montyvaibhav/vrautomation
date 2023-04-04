package com.qa.base;

//import com.sdetblueprint.utils.DriverManager;
//import com.sdetblueprint.utils.PropertyReader;
import org.openqa.selenium.WebDriver;
//import com.sdetblueprint.utils.Log;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {

	public WebDriver driver;
//    PropertyReader pr = new PropertyReader( );

	public WebDriver getDriver() {
		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public void setUp() {
		try {
			String browser = BasePage.getProperty("browser");
			if (BasePage.getProperty("browser").equals("Firefox")) {
//                Log.info("Found firefox as Browser");
				driver = new FirefoxDriver();
				driver.manage().window().maximize();

			} 
			else if (browser.equals("Chrome")) {
				String chromePath= System.getProperty("user.dir") + "\\src\\main\\resources\\driver\\chromedriver.exe";
				System.setProperty("webdriver.chrome.driver", chromePath);
//				ChromeOptions chromeOptions = new ChromeOptions();
//				chromeOptions.addArguments("--headless");
				driver = new ChromeDriver();
				driver.manage().window().maximize();
			} 
			else {
				try {
					throw new Exception("Browser or Browser Driver is not supported yet.");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.getMessage();
					e.printStackTrace();
//                    Log.error("Browser Launch Failure: ", e);
				}
			}
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		} catch (Exception e) {
//            Log.error("Chrome Launch Failure: ", e);

		}
	}

	@AfterMethod(alwaysRun = true)
	public void teardown() {
//        Log.info("Closing Webdriver Windows");
		driver.quit();

	}

}