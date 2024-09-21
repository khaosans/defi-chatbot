package org.sour.agent;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.TokenStream;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface RoutingAgent {

    @SystemMessage("""
            You are a routing assistant for a DeFi chatbot.
            Your task is to direct user inquiries to the appropriate agent based on the content of their messages.
            """)
    TokenStream chat(@MemoryId String chatId, @UserMessage String userMessage);

    // Method to route to PortfolioAgent
    String routeToPortfolio(String userInput);

    // Method to route to LangChain4jAssistant
    String routeToAssistant(String userInput);
}