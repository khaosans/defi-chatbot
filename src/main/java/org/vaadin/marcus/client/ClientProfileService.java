package org.vaadin.marcus.client;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.springframework.stereotype.Service;
<<<<<<< HEAD
import org.vaadin.marcus.service.ClientProfile;
import org.vaadin.marcus.service.ClientService;

import java.util.List;

@BrowserCallable
@AnonymousAllowed
@Service
public class ClientProfileService {
=======
import org.vaadin.marcus.service.ClientService;
import org.vaadin.marcus.service.ClientProfile;

import java.util.List;

@Service
@BrowserCallable
@AnonymousAllowed
public class ClientProfileService {

>>>>>>> c8f49c5 (Update ClientProfileService, ClientManagementModal, and Index components)
    private final ClientService clientService;

    public ClientProfileService(ClientService clientService) {
        this.clientService = clientService;
    }

    public List<ClientProfile> getAllClients() {
<<<<<<< HEAD
        return clientService.getAllClientProfiles();
    }

    public ClientProfile getClientByEmail(String email) {
        // Implement this method in the backend ClientService if needed
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public ClientProfile addNewClient(ClientProfile newClient) {
        clientService.createClientProfile(newClient);
        return newClient;
    }

    public ClientProfile updateClient(ClientProfile updatedClient) {
        clientService.updateClientProfile(updatedClient.getId(), updatedClient);
        return updatedClient;
    }

    public void deleteClient(String clientId) {
        clientService.deleteClientProfile(clientId);
    }

    public List<ClientProfile> getClientsByStatus(String status) {
        // Implement this method in the backend ClientService if needed
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public ClientProfile getClientProfile(String clientId) {
        return clientService.getClientProfile(clientId);
=======
        return clientService.getAllClients();
    }

    // Add this method to generate sample data
    public void generateSampleData() {
        clientService.generateSampleData();
>>>>>>> c8f49c5 (Update ClientProfileService, ClientManagementModal, and Index components)
    }
}