package com.kiwihouse.common.httpclient;

import java.io.IOException;
import java.net.URISyntaxException;

public interface IHttpClient {
	
	/**
	 * Fetch HTTP response by given HTTP request
	 * 
	 * @param request Specify which to be fetched.
	 * @return the response to the request.
	 * @throws IOException connection problem.
	 * @throws URISyntaxException url syntax problem.
	 */
	HttpResponse fetch(HttpRequest request) 
			throws IOException, URISyntaxException;
	
	/**
	 * Close http client and release resource
	 */
	void close();
}
