package org.sour.service;

import dev.langchain4j.agent.tool.Tool;
import dev.langchain4j.service.TokenStream;
import org.sour.agent.LangChain4jAssistant;
import org.sour.agent.PortfolioAgent;
import org.sour.agent.RoutingAgent;
import org.sour.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import static reactor.core.publisher.Sinks.*;

@Service
public class AgentService implements LangChain4jAssistant {

    @Qualifier("langChain4jAssistant")
    @Autowired
    private LangChain4jAssistant langChain4JAssistant;

    @Autowired
    private RoutingAgent routingAgent;

    @Autowired
    private PortfolioAgent portfolioAgent;

    public AgentService(RoutingAgent routingAgent, PortfolioAgent portfolioAgent, @Qualifier("langChain4jAssistant") LangChain4jAssistant langChain4JAssistant) {
        this.routingAgent = routingAgent;
        this.portfolioAgent = portfolioAgent;
        this.langChain4JAssistant = langChain4JAssistant;
    }

    @Tool("""
            routing tool
            """)
    public String handleUserInput(String userInput) {
        // Example routing logic based on user input
        if (userInput.contains("liquidity")) {
            return portfolioAgent.processChatMessage(userInput);
        } else if (userInput.contains("portfolio")) {
            return portfolioAgent.processChatMessage(userInput);
        } else {
            return routingAgent.routeToAssistant(userInput); // Fallback to routing agent
        }
    }

    public Flux<String> chat(String chatId, String userMessage) {
        Many<String> sink = many().unicast().onBackpressureBuffer();
        langChain4JAssistant.chat(chatId, userMessage)
                .subscribe(
                        sink::tryEmitNext,
                        sink::tryEmitError,
                        sink::tryEmitComplete
                );

        return sink.asFlux();
    }
}