package com.qa.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.qa.base.BasePage;


public class FWLoginPage extends BasePage{
    WebDriver driver;

    public FWLoginPage(WebDriver driver){
        super(driver );
        this.driver = driver;
        
        PageFactory.initElements(driver, this);
    }
    // Page Locators
    
    @FindBy(xpath = "//li[@class='nav-item login']/a")
    private WebElement btnLogin;
    
    @FindBy(xpath = "//*[@id='email-input']")
    private WebElement inputUserName;
    
    @FindBy(xpath = "//input[@id='password-input']")
    private WebElement inputPassword;
    
    @FindBy(xpath = "//fw-button[@data-e2e='signin-with-password']")
    private WebElement btnSignIn;
    
    @FindBy(xpath = "//fw-button[@data-e2e='login-email']")
    private WebElement btnNext;
    
    @FindBy(xpath = "//div[@id=\"auth-sign-in\"]/form/img")
    private WebElement imgFWLogo;
  

    // Page Actions

    public void openGoogle(){
        gotoURL(getProperty("applicationUrl"));
    }

    public void openFieldWireApp() {
    	gotoURL(getProperty("fielwireURL")); 	
    	Assert.assertTrue(imgFWLogo.getAttribute("src").contains("Fieldwire-Hilti-logo"));
    }
    public void LoginToFieldWire(String username, String password){
    	openFieldWireApp();
    	
    	inputUserName.sendKeys(username);
    	btnNext.click();
    	
    	inputPassword.sendKeys(password);
    	btnSignIn.click();


    }

    public HomePage afterLogin(){
        return new HomePage(driver);
    }
	
}
