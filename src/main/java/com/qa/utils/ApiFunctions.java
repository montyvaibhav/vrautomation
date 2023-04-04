package com.qa.utils;

import java.io.File;
import java.util.List;
import org.json.simple.JSONObject;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * @author vaibhav rauthan
 * created : 12-01-2023
 * updated : 15-01-2023
 */
public class ApiFunctions implements IApiFunctions{

	public static RequestSpecification reqSpec;
	public ApiOperations method;
	public String url;
	public Response resp;
	
	public ApiFunctions() {
		
	}
	
    /**
     * Method to set the Url for API requests
     */
    public void initOperation(String url, ApiOperations method) {
		this.url = url;
		this.method = method;
		
	}
	
    /**
     * Method to set the Base Url for all API requests
     */
	public void initBaseUrl(String baseConst) {
		try {
			RestAssured.baseURI = baseConst;
		} catch (Exception e) {
			e.printStackTrace();
		}
		reqSpec = RestAssured.given();
	}

	/**
	 * Set header of API Request 
	 */
	public void setHeader(String head, String val) { reqSpec.header(head, val);}

	/**
	 * Set request body with String type
	 */
	public void setBody(String body) { reqSpec.body(body); }
	
	/**
	 * Set request body with JSONObject type
	 */
	public void setBody(JSONObject body) { reqSpec.body(body.toString()); }

	/**
	 * Set request body with Object type
	 */
	public void setBody(Object body) { reqSpec.body(body); }
	
	/**
	 * Set request Query Parameters with key and values
	 */
	public void setQueryParam(String key, String val) { reqSpec.queryParam(key, val);}

	/**
	 * This method is used to call the Fieldwire REST Api end points using
	 * the method set for the API call. 
	 * It returns the String representation of Response 
	 */
	public String callApi() {
		try {
			if (method.toString().equalsIgnoreCase("get")) {
				resp = reqSpec.get(url);
				return resp.asString();
			} else if (method.toString().equalsIgnoreCase("post")) {
				resp = reqSpec.post(url);
				return resp.asString();
			} else if (method.toString().equalsIgnoreCase("patch")) {
				resp = reqSpec.patch(url);
				return resp.asString();
			} else if (method.toString().equalsIgnoreCase("put")) {
				resp = reqSpec.put(url);
				return resp.asString();
			} else if (method.toString().equalsIgnoreCase("delete")) {
				resp = reqSpec.delete(url);
				return resp.asString();
			}
			return "invalid method set for API";
		} catch (Exception e) {
			System.out.println("Exception found during API call: " + e.getStackTrace());
			return "Exception Caught";
		}
	}

	/**
	 * This method returns Response as String
	 */
	public String getResponseString() { return resp.asString();}
	
	/**
	 * This method is used to list Response Headers
	 */
	public void ListResponseHeaders()
	{ 
	 // Get all the headers. Return value is of type Headers.
	 Headers allHeaders = resp.headers();
	 // Iterate over all the Headers
	 for(Header header : allHeaders)
	 {
	 System.out.println("Key: " + header.getName() + " Value: " + header.getValue());
	 }
	}
	
	/**
	 * This method is used to get response Status Code
	 */
	public int getStatusCode() { return resp.getStatusCode(); }
	
	/**
	 * This method is used to return all response Headers
	 */
	public Headers getAllHeaders() {return resp.getHeaders();}
	
	/**
	 * This method is used to return all response object
	 */
	public Response getResponseObject() {
		return resp;
	}
	
	/**
	 * This method is used to return all response in the form of Json path
	 */
	public JsonPath getResponseJsonPath() {
		JsonPath path = new JsonPath(resp.asString());
		return path;
	}
}
