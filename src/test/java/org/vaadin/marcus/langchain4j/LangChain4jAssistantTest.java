package org.vaadin.marcus.langchain4j;

import dev.langchain4j.service.TokenStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class LangChain4jAssistantTest {

    @Autowired
    private LangChain4jAssistant assistant;

    @Test
    public void smokeTest() {
        String chatId = "test-chat-id";
        String userMessage = "Hello, I need help with my booking.";

        TokenStream response = assistant.chat(chatId, userMessage);

        assertNotNull(response, "Response should not be null");
        // Add more assertions as needed
    }
}