package org.vaadin.marcus.langchain4j;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.vaadin.marcus.service.AccountDetails;
import org.vaadin.marcus.service.CoinbaseService;

import dev.langchain4j.agent.tool.Tool;

@Component
public class LangChain4jTools {

    private final CoinbaseService coinbaseService;

    public LangChain4jTools(CoinbaseService coinbaseService) {
        this.coinbaseService = coinbaseService;
    }

    @Tool("Retrieves account details.")
    public AccountDetails getAccount() {
        try {
            return coinbaseService.getAccounts();
        } catch (IOException e) {
            System.err.println("Error fetching account: " + e.getMessage());
            return null;
        }
    }

    // Update the default password method
    @Tool("Creates a default user password.")
    public String createDefaultPassword() {
        String defaultPassword = "password"; // Set your default password here
        return defaultPassword;
    }
}
