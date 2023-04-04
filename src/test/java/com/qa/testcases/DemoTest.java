package com.qa.testcases;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.base.BasePage;
import com.qa.base.BaseTest;
import com.qa.pages.HomePage;
import com.qa.utils.ApiFunctions;
import com.qa.utils.ApiOperations;
import com.qa.utils.Authentication;
import com.qa.utils.Constants;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import com.qa.pages.FWHomePage;
import com.qa.pages.FWLoginPage;
import com.qa.pages.FWProjectPage;

public class DemoTest extends BaseTest{
	public static RequestSpecification reqSpec;
//	public ApiOperations method;
	public String url;
	public Response resp;
	public static String projectId;
	public static String accountId;
	public static String projectName = "Test Aut Project";
	public static String address = "Paris, France";
	public static String currency = "EURO";
	public static String color = "Blue";
	ArrayList<String> projects;
	ApiFunctions objApiFunctions;

	@BeforeClass
	public void setup() {
		String filePath = System.getProperty("user.dir") + "//src//main//config//config.properties";
		BasePage.init(filePath);
		System.out.println("URL is : " + BasePage.getProperty("applicationUrl"));
	}
	
	@Test(enabled = false)
	public void VerifyHomePageTest() {
		HomePage homePageObj = new HomePage(getDriver());
	}
	
//    @Test
//    public void loginVWO() {
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.openVwo( );
//        loginPage.LoginWithValidCreds( );
//    }
//
//    @Test
//    public void loginAndVerifyDashboard() {
//
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.openVwo( );
//        loginPage.LoginWithValidCreds( );
//        DashboardPage dashboardPage = loginPage.afterLogin( );
//        Assert.assertEquals(dashboardPage.loggedInUserName( ), "Wingify");
//
//    }
	
	@Test
	public void VerifyLoginTest() {
		FWLoginPage fwLoginPage = new FWLoginPage(getDriver());
		fwLoginPage.LoginToFieldWire(BasePage.getProperty("userId"), BasePage.getProperty("password"));
		FWHomePage fwHomePageObj = new FWHomePage(getDriver());
		fwHomePageObj.validateHomePage();
	}
	
	@Test
	public void VerifyProjectsTest() {		
		//Get Projects from UI
		FWProjectPage objFWProjectPage = new FWProjectPage(getDriver());
		FWLoginPage fwLoginPage = new FWLoginPage(getDriver());
		fwLoginPage.LoginToFieldWire(BasePage.getProperty("userId"), BasePage.getProperty("password"));
		FWHomePage fwHomePageObj = new FWHomePage(getDriver());
		fwHomePageObj.validateHomePage();
		objFWProjectPage.validateProjectPage();	
		List<String> projectsUI = objFWProjectPage.getProjects();
		
		//Get projects from App using API call
		Authentication auth = new Authentication();
		objApiFunctions = new ApiFunctions();
		auth.generateCredentials(Constants.email, Constants.password);
		objApiFunctions.setHeader(Constants.FIELDWIRE_USER_ID_KEY, Authentication.userId);
		objApiFunctions.setHeader(Constants.FIELDWIRE_USER_AUTH_TOKEN_KEY, Authentication.userAuthToken);
		objApiFunctions.setHeader("Fieldwire-Filter", "active");
	    accountId = Authentication.accountId;
	    
	    
	    objApiFunctions.initOperation(Constants.GET_PROJECTS_PATH, ApiOperations.GET);
	    objApiFunctions.setBody("");
		String apiResponse = objApiFunctions.callApi();
//		System.out.println(objApiFunctions.getResponseObject().prettyPrint());
		Assert.assertEquals(objApiFunctions.getStatusCode(), Constants.STATUS_CODE_200,	"** Status code does not match **");

		//Validate project id is not null
		JsonPath projectDetails = new JsonPath(apiResponse);
		
		//Get list of Projects
		
		List<String> projects = projectDetails.get("name"); //get("store.book.category");
//		initOperation(Constants.CREATE_PROJECT_PATH + "/" + projId + Constants.GET_TASK_PATH, ApiOperations.GET);
//		setBody("");
//		if(paramKey!=null && paramValue!=null)
//			setQueryParam(paramKey, paramValue);
//		return callApi();
//		System.out.println("NUMBER OF PROJECTS: " + projects.size());
//		System.out.println("\n---------------------PROJETCS------------------\n" + projects);
		
		
		
		validateProjectsData(projectsUI,projects);
		
	}
	
	public void validateProjectsData(List<String> uiProjects, List<String> apiProjects) {
	
		Assert.assertTrue(uiProjects.size()==apiProjects.size(), "Projects count does not match in UI and API response");
		
		for(int i=0;i<uiProjects.size();i++) {
			Assert.assertEquals(uiProjects.get(i), apiProjects.get(i), "UI and API Project name does not match");
		}
	}
	
	
}
