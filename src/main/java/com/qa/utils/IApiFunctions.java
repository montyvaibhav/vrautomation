package com.qa.utils;

import org.json.simple.JSONObject;

/**
 * @author vaibhav rauthan
 * created : 12-01-2023
 * updated : 14-01-2023
 */
public interface IApiFunctions {

	public void initOperation(String url, ApiOperations method);

	public void setHeader(String head, String val);

	public void setBody(String body);
	
	public void setBody(JSONObject object);
	
	public void setBody(Object object);

	public void setQueryParam(String key, String val);

	public String callApi();

	public String getResponseString();

}
