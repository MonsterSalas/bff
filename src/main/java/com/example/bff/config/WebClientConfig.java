package com.example.bff.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder.baseUrl("https://funcioncloudnative1.azurewebsites.net/api").build();
    }

    @Bean
    public WebClient roleWebClient(WebClient.Builder builder) {
        return builder.baseUrl("https://funcioncloudnative2.azurewebsites.net/api").build();
    }
}

