package com.kiwihouse.common.httpclient;

import org.apache.http.Header;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public class PoolingHttpClient implements IHttpClient {

	private PoolingHttpClientConnectionManager connMgr;
	private CloseableHttpClient client;
	
	public PoolingHttpClient(int maxConnection) {
		this.connMgr = new PoolingHttpClientConnectionManager();
		connMgr.setMaxTotal(maxConnection);
		
		this.client = HttpClients.custom()
				.setConnectionManager(this.connMgr)
				.build();
	}
	
	public PoolingHttpClient() {
		this(512);
	}
	
	public HttpResponse fetch(HttpRequest request) throws IOException, URISyntaxException {
		
		URIBuilder uriBuilder = new URIBuilder(request.url);
		for(Map.Entry<String, String> entry : request.parameters.entrySet()) {
			uriBuilder.addParameter(entry.getKey(), entry.getValue());
		}
		
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(request.connectTimeout)
				.setConnectionRequestTimeout(request.requestTimeout)
				.build();
		
		RequestBuilder requestBuilder = RequestBuilder.create(request.method.name())
				.setUri(uriBuilder.build())
				.setEntity(new ByteArrayEntity(request.body.getBytes(request.bodyCharset)))
				.setConfig(requestConfig);
		
		for(Map.Entry<String, String> entry : request.headers.entrySet()) {
			requestBuilder.setHeader(entry.getKey(), entry.getValue());
		}
		
		HttpClientContext context = HttpClientContext.create();
		CloseableHttpResponse response = client.execute(requestBuilder.build(), context);
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
	}

	public synchronized void close() {
		if(client != null) {
			try {
				client.close();
			} catch (IOException e) {
			}
			client = null;
		}
		if(connMgr != null) {
			connMgr.close();
			connMgr = null;
		}
	}
}
