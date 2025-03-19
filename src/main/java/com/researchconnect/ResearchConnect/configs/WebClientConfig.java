 package com.researchconnect.ResearchConnect.configs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.beans.factory.annotation.Value;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Configuration
public class WebClientConfig {
    private static final Logger logger = LoggerFactory.getLogger(WebClientConfig.class);

    @Value("${supabase.database.url}")
    private String supabaseUrl;
    @Value("${supabase.api.key}")
    private String supabasekey;

    @Bean
    public WebClient webClient(){

        return WebClient.builder().baseUrl(supabaseUrl)
        .defaultHeader("apikey",supabasekey)
        .defaultHeader("Authorization", "Bearer " + supabasekey)
        .build();

    }
}
