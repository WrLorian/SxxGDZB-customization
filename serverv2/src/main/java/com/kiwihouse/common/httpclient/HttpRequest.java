package com.kiwihouse.common.httpclient;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class HttpRequest {
	
	public String url;
	public HttpMethod method;
	public String body;
	public HashMap<String, String> headers;
	public HashMap<String, String> parameters;
	public int connectTimeout;
	public int requestTimeout;
	public Charset bodyCharset;
	
	public HttpRequest(final HttpMethod method, final String url) {
		super();
		this.method = method;
		this.url = url;
		
		this.headers = new HashMap<String, String>();
		this.parameters = new HashMap<String, String>();
		this.bodyCharset = StandardCharsets.UTF_8;
	}
	
	public HttpRequest setBody(String body) {
		this.body = body;
		return this;
	}

	public HttpRequest setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
		return this;
	}

	public HttpRequest setRequestTimeout(int requestTimeout) {
		this.requestTimeout = requestTimeout;
		return this;
	}

	public HttpRequest setBodyCharset(Charset bodyCharset) {
		this.bodyCharset = bodyCharset;
		return this;
	}
	
	public HttpRequest addHeader(String name, String value) {
		this.headers.put(name, value);
		return this;
	}
	
	public HttpRequest addQueryParameter(String name, String value) {
		this.parameters.put(name, value);
		return this;
	}
	
	public HttpRequest addQueryParameter(String name, int value) {
		this.parameters.put(name, String.valueOf(value));
		return this;
	}
	
	public HttpRequest addQueryParameter(String name, long value) {
		this.parameters.put(name, String.valueOf(value));
		return this;
	}
}
