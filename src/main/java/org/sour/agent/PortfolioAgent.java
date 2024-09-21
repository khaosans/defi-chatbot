package org.sour.agent;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface PortfolioAgent {

    @SystemMessage("""
            You are a liquidity assistant for a DeFi chatbot.
            Your task is to handle user inquiries related to liquidity operations.
            """)
    String processChatMessage(String userInput);
}