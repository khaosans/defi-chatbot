// src/main/java/org/vaadin/marcus/config/AppConfig.java

package org.sour.config;

import com.vaadin.flow.component.login.LoginForm;
import org.sour.agent.LangChain4jAssistant;
import org.sour.endpoint.AssistantEndpoint;
import org.sour.service.AgentService;
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


    @Bean
    public AssistantEndpoint assistantEndpoint(AgentService agentService) {
        return new AssistantEndpoint(agentService);
    }


    @Bean
    public LoginForm loginForm() {
        return new LoginForm();
    }
}

