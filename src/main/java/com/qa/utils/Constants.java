package com.qa.utils;

/**
 * @author vaibhav rauthan
 * created : 12-01-2023
 * updated : 15-01-2023
 */
public class Constants {

	public static String email="vaibhavrauthan18@gmail.com";
	public static String password = "Monty@1989";
	
	//************** URL PATH START ******************
	public static String BASE_URI = "https://app.fieldwire.com/api/v3";
	public static String SIGN_IN_PATH = "/sign_in";
	public static String CREATE_PROJECT_PATH = "/projects";
	public static String GET_PROJECTS_PATH ="/account/projects";
	public static String CREATE_TASK_PATH ="/tasks";
	public static String GET_TASK_PATH ="/tasks";
	public static String GET_STATUS_PATH = "/statuses";
	//************** URL PATH END ******************
	
	//************** REQUEST HEADERS START******************
	public static String CONTENT_TYPE_KEY = "Content-Type";
	public static String ACCEPT_KEY = "Accept";
	public static String CONTENT_TYPE_VALUE_JSON = "application/json";
	public static String ACCEPT_VALUE_JSON = "application/json";
	public static String FIELDWIRE_USER_ID_KEY = "Fieldwire-User-Id";
	public static String FIELDWIRE_USER_AUTH_TOKEN_KEY = "Fieldwire-User-Token";
	public static String FIELDWIRE_VERSION_KEY = "Fieldwire-Version";
	public static String FIELDWIRE_VERSION_VALUE = "2020-06-22";
	
	//************** REQUEST HEADERS END******************
	
	//************** RESPONSE HEADERS START******************
	public static String RES_HEAD_CONTENT_TYPE_VALUE_JSON = "application/json; charset=utf-8";
	public static String RES_HEAD_CURRENT_USER_KEY = "X-Current-User-Id";
	public static String RES_HEAD_SET_COOKIE_KEY = "Set-Cookie";
	
	
	
	//************** RESPONSE HEADERS END******************
	
	//************** PAYLOAD AND RESPONSE CONSTANTS START ******************
	public static String EMAIL_KEY = "email";
	public static String PASSWORD_KEY = "password";
	public static String USER_LOGIN_KEY = "user_login";
	public static String PROJECT_ID_KEY = "id";
	public static String PROJECT_NAME_KEY = "name";
	public static String TASK_PRIORITY_KEY = "priority";
	public static String AUTH_TOKEN_KEY = "auth_token";
	public static String USER_ID_PATH = "user.id";
	public static String ACCOUNT_ID_PATH = "user.account_id";
	public static int PRIORITY_0 =0;
	public static int PRIORITY_1 =1;
	public static int PRIORITY_2 =2;
	public static int PRIORITY_3 =3;
	//************** PAYLOAD AND RESPONSE CONSTANTS END ******************
	
	//************** STATUS CODES START ******************
	public static int STATUS_CODE_200 = 200;
	public static int STATUS_CODE_201 = 201;
	public static int STATUS_CODE_204 = 204;
	public static int STATUS_CODE_401 = 401;
	public static int STATUS_CODE_422 = 422;
	
	//************** STATUS CODES END ******************
	
	//************** ERROR MESSAGES STARTS ******************
	public static String ERR_MSG_INVALID_PROJECT_NAME = "Name can't be blank";
	public static String ERR_MSG_NO_OWNER_ID = "Owner must belong to project";
	
	//************** ERROR MESSAGES ENDS ******************
	
}
