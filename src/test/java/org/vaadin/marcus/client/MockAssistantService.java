package org.vaadin.marcus.client;

import org.vaadin.marcus.langchain4j.LangChain4jAssistant;

public class MockAssistantService extends AssistantService {
    public MockAssistantService(LangChain4jAssistant langChain4jAssistant) {
        super(langChain4jAssistant);
    }

    public String getResponse(String input) {
        // Simulate a response based on the input
        if ("hello".equalsIgnoreCase(input)) {
            return "Hello! How can I assist you today?";
        }
        return "I'm not sure how to respond to that.";
    }
}