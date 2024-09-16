package org.vaadin.marcus.client;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.springframework.stereotype.Service;
import org.vaadin.marcus.service.ClientService;
import org.vaadin.marcus.service.ClientProfile;

import java.util.List;

@Service
@BrowserCallable
@AnonymousAllowed
public class ClientProfileService {

    private final ClientService clientService;

    public ClientProfileService(ClientService clientService) {
        this.clientService = clientService;
    }

    public List<ClientProfile> getAllClients() {
        return clientService.getAllClients();
    }

    // Add this method to generate sample data
    public void generateSampleData() {
        clientService.generateSampleData();
    }
}