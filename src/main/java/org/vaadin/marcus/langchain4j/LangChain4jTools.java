package org.vaadin.marcus.langchain4j;

import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;
import org.vaadin.marcus.service.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Component
public class LangChain4jTools {

    private final FlightService flightService;
    private final ClientService clientService;

    public LangChain4jTools(FlightService flightService, ClientService clientService) {
        this.flightService = flightService;
        this.clientService = clientService;
    }

    @Tool("Retrieves information about an existing booking")
    public BookingDetails getBookingDetails(String bookingNumber, String firstName, String lastName) {
        return flightService.getBookingDetails(bookingNumber, firstName, lastName);
    }

    @Tool("Modifies an existing booking")
    public void changeBooking(String bookingNumber, String firstName, String lastName,
                              LocalDate newFlightDate, String newDepartureAirport, String newArrivalAirport) {
        flightService.changeBooking(bookingNumber, firstName, lastName, newFlightDate, newDepartureAirport, newArrivalAirport);
    }

    @Tool("Cancels an existing booking")
    public void cancelBooking(String bookingNumber, String firstName, String lastName) {
        flightService.cancelBooking(bookingNumber, firstName, lastName);
    }

    @Tool("Confirms an existing booking")
    public void confirmBooking(String bookingNumber, String firstName, String lastName) {
        flightService.confirmBooking(bookingNumber, firstName, lastName);
    }

    @Tool("Retrieves a list of available bookings")
    public List<BookingDetails> getAvailableBookings() {
        return flightService.getAvailableBookings();
    }

    @Tool("Get client profile")
    public String getClientProfile(String clientId) {
        ClientProfile profile = clientService.getClientProfile(clientId);
        return profile != null ? profile.toString() : "Client profile not found.";
    }

    @Tool("Update or add a new client")
    public String updateOrAddClient(String clientId, String name, String contactInfo, String preferences) {
        Map<String, String> preferencesMap = parsePreferences(preferences);
        ClientProfile existingProfile = clientService.getClientProfile(clientId);

        if (existingProfile != null) {
            // Update existing client
            existingProfile.setName(name);
            existingProfile.setContactInfo(contactInfo);
            existingProfile.setPreferences(preferencesMap);
            clientService.updateClientProfile(clientId, existingProfile);
            return "Client profile updated successfully for ID: " + clientId;
        } else {
            // Add new client
            ClientProfile newProfile = new ClientProfile(clientId, name, contactInfo, preferencesMap);
            clientService.createClientProfile(clientId, newProfile);
            return "New client profile created with ID: " + clientId;
        }
    }

    private Map<String, String> parsePreferences(String preferences) {
        Map<String, String> preferencesMap = new HashMap<>();
        if (preferences != null && !preferences.isEmpty()) {
            String[] pairs = preferences.split(",");
            for (String pair : pairs) {
                String[] keyValue = pair.split(":");
                if (keyValue.length == 2) {
                    preferencesMap.put(keyValue[0].trim(), keyValue[1].trim());
                }
            }
        }
        return preferencesMap;
    }
}
