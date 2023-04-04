package com.qa.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.base.BasePage;

import junit.framework.Assert;

public class FWHomePage extends BasePage{
	
	WebDriverWait wait;
	public FWHomePage(WebDriver driver){
        super(driver );
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
	
    @FindBy(xpath = "//span[@class='user-firstname']")
    private WebElement labelUserName;
    
    @FindBy(xpath = "//div[@class=\"navbar-brand\"]/a/span[text()='FIELDWIRE']")
    private WebElement labelFWBrandLogo;
    
    public void validateHomePage() {
    	Assert.assertTrue(labelFWBrandLogo.isDisplayed());
    	wait.until(ExpectedConditions.elementToBeClickable(labelUserName));
    	Assert.assertEquals(labelUserName.getText().trim(),getProperty("loggedInUserName")); 	
    }
}
