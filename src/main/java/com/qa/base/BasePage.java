package com.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	private static final int TIMEOUT = 5;
	private static final int POLLING = 100;
	public WebDriver driver;
	private WebDriverWait wait;

	static File file;
	static Properties properties;
	static FileInputStream fInputStream;

	public BasePage(WebDriver driver) {
		this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(POLLING));
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), this);
	}

	public static void init(String filePath) {
		file = new File(filePath);
		try {
			fInputStream = new FileInputStream(file);
			properties = new Properties();
			properties.load(fInputStream);
		} catch (FileNotFoundException e) {
			System.out.println("File Not found: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Input Output error: " + e.getMessage());
		}
	}

    protected void waitForElementToAppear(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
	public static String getProperty(String property) {
		return properties.getProperty(property);
	}

	// open url method
	public void gotoURL(String url) {
		driver.get(url);
	}

    //wait method
//    public void waitForElementToAppear(By elementLocation) {
//        //Conditional wait for one of the elements on the search results page to be present
//        new WaitForHelper(driver).presenceOfTheElement(elementLocation);
//    }
//
//    public void waitForTime() {
//        //Conditional wait for one of the elements on the search results page to be present
//        new WaitForHelper(driver).implicitwait( );
//    }

	// Click Method
	public void click(By elementLocation) {
		driver.findElement(elementLocation).click();
	}

	// Write Text
	public void writeText(By elementLocation, String text) {
		driver.findElement(elementLocation).clear();
		driver.findElement(elementLocation).sendKeys(text);
	}

	// Read Text
	public String readText(By elementLocation) {
		return driver.findElement(elementLocation).getText();
	}

	// Move to Element
//	public void moveToElement(By elementLocation) {
//
//		new Actions(driver).moveToElement(driver.findElement(elementLocation)).build().perform();
//
//	}
}
