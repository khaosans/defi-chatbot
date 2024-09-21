package org.sour.service;

import org.sour.agent.PortfolioAgent;
import org.sour.agent.RoutingAgent;
import org.sour.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgentService {

    @Autowired
    private RoutingAgent routingAgent;

    @Autowired
    private PortfolioAgent portfolioAgent;

    private String defaultAgentName; // {{ edit_1: Add default agent name field }}

    public AgentService(String defaultAgentName) {
        this.defaultAgentName = defaultAgentName; // {{ edit_2: Initialize default agent name }}
    }

    public Message createMessage(String content) { // {{ edit_3: Method to create message with agent name }}
        return new Message(content, defaultAgentName); // {{ edit_4: Always include agent name }}
    }

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
}