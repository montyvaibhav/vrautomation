package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.BasePage;

public class ContentSquarePage extends BasePage{
	WebDriver driver;

    public ContentSquarePage(WebDriver driver){
        super(driver );
        this.driver = driver;
        
        PageFactory.initElements(driver, this);
    }
    // Page Locators
    
    @FindBy(xpath = "//body/pre")
    private WebElement pageText;
    
    public void openContentSuarePage(){
        gotoURL(getProperty("contentsquare_url") + "/comments");
    }
    
    public String getPageData() {
    	String data = pageText.getText();
    	return data;
    }
}
