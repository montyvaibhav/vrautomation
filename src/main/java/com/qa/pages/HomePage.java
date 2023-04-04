package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.base.BasePage;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver){
        super(driver );
    }

    By userName  = By.cssSelector("[data-qa='lufexuloga']");

    // Page Action
//    public String loggedInUserName(){
//        waitForElementToAppear(userName);
//        return driver.findElement(userName).getText();
//    }
}
