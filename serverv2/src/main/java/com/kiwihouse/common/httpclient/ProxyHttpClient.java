package com.kiwihouse.common.httpclient;

import org.apache.http.Header;
import org.apache.http.HttpHost;
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

public class ProxyHttpClient implements IHttpClient {

	private HttpHost proxy;
	
	public ProxyHttpClient(String host) {
		this.proxy = new HttpHost(host);
	}
	
	public ProxyHttpClient(String host, int port) {
		this.proxy = new HttpHost(host, port);
	}
	
	public ProxyHttpClient(String host, int port, String scheme) {
		this.proxy = new HttpHost(host, port, scheme);
	}
	
	public HttpResponse fetch(HttpRequest request) throws IOException, URISyntaxException {
		
		URIBuilder uriBuilder = new URIBuilder(request.url);
		for(Map.Entry<String, String> entry: request.parameters.entrySet()) {
			uriBuilder.addParameter(entry.getKey(), entry.getValue());
		}
		
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(3 * 60 * 1000)
				.setConnectTimeout(request.connectTimeout)
				.setConnectionRequestTimeout(request.requestTimeout)
				.setProxy(this.proxy)
				.build();
		
		RequestBuilder requestBuilder = RequestBuilder.create(request.method.name())
				.setUri(uriBuilder.build())
				.setEntity(new ByteArrayEntity(request.body.getBytes(request.bodyCharset)))
				.setConfig(requestConfig);
		for(Map.Entry<String, String> entry: request.headers.entrySet()) {
			requestBuilder.setHeader(entry.getKey(), entry.getValue());
		}
		
		CloseableHttpClient client = HttpClients.createDefault();
		
		try {
			CloseableHttpResponse response = client.execute(requestBuilder.build());
			try {
				HttpResponse res = new HttpResponse()
						.setRequest(request)
						.setStatusCode(response.getStatusLine().getStatusCode())
						.setReason(response.getStatusLine().getReasonPhrase())
						.setBody(EntityUtils.toString(response.getEntity(), "UTF-8"));
				for(Header header: response.getAllHeaders()) {
					res.addHeader(header.getName(), header.getName());
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

	}
}
