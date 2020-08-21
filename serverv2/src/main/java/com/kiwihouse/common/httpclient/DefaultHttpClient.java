package com.kiwihouse.common.httpclient;

import org.apache.http.Header;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public class DefaultHttpClient implements IHttpClient {

	public HttpResponse fetch(HttpRequest request) throws IOException, URISyntaxException {
		
		URIBuilder uriBuilder = new URIBuilder(request.url);
		for(Map.Entry<String, String> entry: request.parameters.entrySet()) {
			uriBuilder.addParameter(entry.getKey(), entry.getValue());
		}
		
		RequestConfig rConfig = RequestConfig.custom()
				.setSocketTimeout(3 * 60 * 1000)
				.setConnectTimeout(request.connectTimeout)
				.setConnectionRequestTimeout(request.requestTimeout)
				.build();
		
		RequestBuilder rBuilder = RequestBuilder.create(request.method.name())
				.setUri(uriBuilder.build())
				.setEntity(new ByteArrayEntity(request.body.getBytes(request.bodyCharset)))
				.setConfig(rConfig);
		
		for(Map.Entry<String, String> entry: request.headers.entrySet()) {
			rBuilder.setHeader(entry.getKey(), entry.getValue());
		}
		
		CloseableHttpClient client = HttpClients.createDefault();
		
		try {
			CloseableHttpResponse response = client.execute(rBuilder.build());
			try {
				HttpResponse res = new HttpResponse()
						.setRequest(request)
						.setStatusCode(response.getStatusLine().getStatusCode())
						.setReason(response.getStatusLine().getReasonPhrase())
						.setBody(EntityUtils.toString(response.getEntity(), "UTF-8"));
				for(Header header: response.getAllHeaders()) {
					res.addHeader(header.getName(), header.getValue());
				}
				return res;
			} finally {
				response.close();
			}
		} finally {
			client.close();
		}
	}

	public void close() {
		// Do nothing
	}
}
