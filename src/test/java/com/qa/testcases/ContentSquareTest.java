package com.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.base.BasePage;
import com.qa.base.BaseTest;
import com.qa.pages.ContentSquarePage;
import com.qa.pages.HomePage;
import com.qa.utils.ApiFunctions;
import com.qa.utils.ApiOperations;
import com.qa.utils.Authentication;
import com.qa.utils.Constants;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ContentSquareTest extends BaseTest{
	ApiFunctions objApiFunctions;
	public static RequestSpecification reqSpec;
	public String url;
	public Response resp;
	
	@BeforeClass
	public void setup() {
		String filePath = System.getProperty("user.dir") + "//src//main//config//config.properties";
		BasePage.init(filePath);
		System.out.println("URL is : " + BasePage.getProperty("contentsquare_url"));
	}
	
	@Test
	public void VerifyUiAndApiData() {
		//Get data from API
//		Authentication auth = new Authentication();
		ContentSquarePage objContentSquarePage = new ContentSquarePage(getDriver());
		objContentSquarePage.openContentSuarePage();
		objApiFunctions = new ApiFunctions();
		objApiFunctions.initBaseUrl(BasePage.getProperty("contentsquare_url"));
//		auth.generateCredentials(Constants.email, Constants.password);
	    
	    
	    objApiFunctions.initOperation("/comments", ApiOperations.GET);
	    objApiFunctions.setBody("");
		String apiResponse = objApiFunctions.callApi();
		System.out.println(apiResponse);
		
		String uiData = objContentSquarePage.getPageData();
//		System.out.println("DATA from UI: \n" + uiData);
		Assert.assertEquals(apiResponse, uiData, "Data do not match");
		
	}
}
