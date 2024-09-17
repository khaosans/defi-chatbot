package org.vaadin.marcus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vaadin.marcus.langchain4j.LangChain4jTools;
import org.vaadin.marcus.service.AccountDetails;

@SpringBootApplication
@RestController
public class Application {

    private final LangChain4jTools langChain4jTools;

    public Application(LangChain4jTools langChain4jTools) {
        this.langChain4jTools = langChain4jTools;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @GetMapping("/account")
    public AccountDetails getAccount() {
        return langChain4jTools.getAccount();
    }
}
