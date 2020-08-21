package com.kiwihouse.common.httpclient;

public class HttpException extends Exception {
	
	private final int statusCode;
	public int getStatusCode() {
		return statusCode;
	}

	private final String reason;
	
	public String getReason() {
		return reason;
	}

	public HttpException(final int statusCode, final String reason) {
		super("Http statusCode: " + statusCode + ", reasons: " + reason);
		this.reason = reason;
		this.statusCode = statusCode;
	}
}
