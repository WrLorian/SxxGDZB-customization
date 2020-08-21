package com.kiwihouse.common.httpclient;

import java.util.HashMap;

public class HttpResponse {
	
	public HttpRequest request;
	public int statusCode;
	public String reason;
	public String body;
	public HashMap<String, String> headers;
	
	public HttpResponse() {
		this.headers = new HashMap<String, String>();
	}
	
	public HttpResponse(final int statusCode) {
		this();
		this.statusCode = statusCode;
	}
	
	public HttpResponse(final int statusCode, final String body) {
		this(statusCode);
		this.body = body;
	}
	
	public HttpResponse(final int statusCode, final String body, final String reason) {
		this(statusCode, body);
		this.reason = reason;
	}
	
	public HttpResponse setRequest(HttpRequest request) {
		this.request = request;
		return this;
	}

	public HttpResponse setStatusCode(int statusCode) {
		this.statusCode = statusCode;
		return this;
	}

	public HttpResponse setReason(String reason) {
		this.reason = reason;
		return this;
	}

	public HttpResponse setBody(String body) {
		this.body = body;
		return this;
	}

	public HttpResponse addHeader(String name, String value) {
		headers.put(name, value);
		return this;
	}
}
