package org.sour.endpoint;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.sour.agent.RoutingAgent;
import org.sour.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

// Ensure this class is annotated as a REST controller
@BrowserCallable
@AnonymousAllowed
@Component
public class AssistantEndpoint implements RoutingAgent {

    // Inject the LangChain4jAssistant
    @Autowired
    private AgentService agentService;

    @Autowired
    public AssistantEndpoint(AgentService agentService) {
        this.agentService = agentService;
    }

    @Override
    public Flux<String> chat(String chatId, String userMessage) {
        return agentService.chat(chatId, userMessage);
    }

    @Override
    public String routeToAssistant(String userInput) {
        return "";
    }

}