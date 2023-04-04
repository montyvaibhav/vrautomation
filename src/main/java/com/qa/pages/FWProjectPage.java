package com.qa.pages;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.BasePage;

public class FWProjectPage extends BasePage{
	

	
	WebDriver driver;

    public FWProjectPage(WebDriver driver){
        super(driver );
        this.driver = driver;
        
        PageFactory.initElements(driver, this);
    }
    // Page Locators
    
    @FindBy(xpath = "//div[@class='fw-user-info']/button/span[1]")
    private WebElement labelUser;
    
    @FindBy(xpath = "//div[@class='project-card']/div/div[1]/div")
    private List<WebElement> labelProjectNames;
    
//    
//    @FindBy(id = "//input[@name='password-input']")
//    private WebElement inputPassword;
//    
//    @FindBy(id = "//fw-button[@data-e2e='signin-with-password']")
//    private WebElement btnSignIn;
//    
//    @FindBy(xpath = "//fw-button[@data-e2e='login-email']")
//    private WebElement btnNext;
//    
//    @FindBy(xpath = "//div[@id=\"auth-sign-in\"]/form/img")
//    private WebElement imgFWLogo;
//    
//    @FindBy(xpath = "//div[@class=\"navbar-brand\"]/a/span[text()='FIELDWIRE']")
//    private WebElement labelFWBrandLogo;
  

    // Page Actions

    public void validateProjectPage() {
    	
    	Assert.assertTrue(labelUser.isDisplayed());
    	Assert.assertEquals("Logged in user does not match", BasePage.getProperty("loggedInUserName").trim(), labelUser.getText());
    	
    }
    
    public List<String> getProjects() {	
    	List<String> projects = new ArrayList<>();
    	for(int i=1;i<labelProjectNames.size();i++) {
    		projects.add(labelProjectNames.get(i).getText());
    	}
    	return projects;
    }

}
