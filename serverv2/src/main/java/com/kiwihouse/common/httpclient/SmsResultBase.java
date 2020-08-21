package com.kiwihouse.common.httpclient;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class SmsResultBase {
	
	protected HttpResponse response;

	/**
	 * Parse result from HttpResponse
	 * @param response HTTP response from api return
	 * @return SmsResultbase
	 * @throws JSONException json parse exception
	 */
	public abstract SmsResultBase parseFromHttpResponse(HttpResponse response)
			throws JSONException;
	
	public JSONObject parseToJson(HttpResponse response) throws JSONException {
		this.response = response;
		return new JSONObject(response.body);
	}
	
	public HttpResponse getResponse() {
		return response;
	}
	
	@Override
	public String toString() {
		return response.body;
	}
}
