package com.kiwihouse.util.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
@PropertySource("application.properties")
@ConfigurationProperties(prefix = "kiwihouse")
@Component
public class GlobalConfig {
    @Value("${kiwihouse.download.url}")
    public String downloadUrl;

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
    
}
