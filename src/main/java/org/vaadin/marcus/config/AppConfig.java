// src/main/java/org/vaadin/marcus/config/AppConfig.java

package org.vaadin.marcus.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Value("${infura.api.key}")
    private String infuraApiKey;

    @Bean
    public String infuraApiKey() {
        return infuraApiKey;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}