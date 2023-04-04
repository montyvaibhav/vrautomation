package com.qa.utils;

import java.util.HashMap;

import org.json.simple.JSONObject;

import com.qa.base.BasePage;

import io.restassured.path.json.JsonPath;

/**
 * @author vaibhav rauthan
 * created : 12-01-2023
 * updated : 12-01-2023
 */
public class Authentication extends ApiFunctions {

	public static String userAuthToken;
	public static String userId;
	public static String accountId;
	private static String authResponse;
	ApiFunctions apiFunctions;
	
	public Authentication() {
	}

	/**
	 * Creates a new auth token to use for access to the API
	 * 
	 */
	private void createToken(String email, String password) {
		apiFunctions = new ApiFunctions();
		initBaseUrl(BasePage.getProperty("BASE_URI"));
		initOperation(Constants.SIGN_IN_PATH, ApiOperations.POST);
		setHeader(Constants.CONTENT_TYPE_KEY, Constants.CONTENT_TYPE_VALUE_JSON);
		setHeader(Constants.ACCEPT_KEY, Constants.ACCEPT_VALUE_JSON);
		setHeader(Constants.FIELDWIRE_VERSION_KEY,Constants.FIELDWIRE_VERSION_VALUE);
		HashMap<String,String> loginParams = new HashMap<String,String>();
		HashMap<String,Object> loginDataMap = new HashMap<String,Object>();
		loginParams.put(Constants.EMAIL_KEY, email);
		loginParams.put(Constants.PASSWORD_KEY, password);
		JSONObject loginCred = new JSONObject(loginParams);
		loginDataMap.put(Constants.USER_LOGIN_KEY, loginCred);
		JSONObject loginData = new JSONObject(loginDataMap);
		setBody(loginData);		
	}

	/**
	 * @param userName (Username string value for the Fieldwire sign in API)
	 *        passWord (password string value for the Fieldwire sign in
	 *                 API)
	 */
	public void generateCredentials(String email, String password) {
		createToken(email, password);
		authResponse = callApi();
		// This auth token and userId will be used in later requests
		JsonPath userPath = new JsonPath(authResponse);
		userAuthToken = userPath.getString(Constants.AUTH_TOKEN_KEY);
		userId = userPath.getString(Constants.USER_ID_PATH);
		accountId= userPath.getString(Constants.ACCOUNT_ID_PATH);
	}



}
