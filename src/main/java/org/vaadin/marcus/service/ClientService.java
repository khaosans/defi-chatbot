package org.vaadin.marcus.service;

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class ClientService {
    private Map<String, ClientProfile> clientProfiles = new HashMap<>();
    private Map<String, List<Booking>> bookingHistory = new HashMap<>();
    private Map<String, List<Interaction>> clientInteractions = new HashMap<>();

    public void createClientProfile(String clientId, ClientProfile profile) {
        clientProfiles.put(clientId, profile);
    }

    public ClientProfile getClientProfile(String clientId) {
        return clientProfiles.get(clientId);
    }

    public void updateClientProfile(String clientId, ClientProfile profile) {
        clientProfiles.put(clientId, profile);
    }

    public void deleteClientProfile(String clientId) {
        clientProfiles.remove(clientId);
    }

    public List<ClientProfile> searchClients(String query) {
        if (query == null || query.isEmpty()) {
            return new ArrayList<>(clientProfiles.values());
        }
        return clientProfiles.values().stream()
                .filter(profile -> profile.getName().toLowerCase().contains(query.toLowerCase())
                        || profile.getContactInfo().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void addBooking(String clientId, Booking booking) {
        bookingHistory.computeIfAbsent(clientId, k -> new ArrayList<>()).add(booking);
    }

    public List<Booking> getBookingHistory(String clientId) {
        return bookingHistory.getOrDefault(clientId, new ArrayList<>());
    }

    public void logInteraction(String clientId, Interaction interaction) {
        clientInteractions.computeIfAbsent(clientId, k -> new ArrayList<>()).add(interaction);
    }

    public List<Interaction> getClientInteractions(String clientId) {
        return clientInteractions.getOrDefault(clientId, new ArrayList<>());
    }

    public List<ClientProfile> segmentClients(SegmentationCriteria criteria) {
        // Implement segmentation logic
        return new ArrayList<>();
    }

    public void updateProfile(String clientId, Map<String, String> profileMap) {
        ClientProfile profile = clientProfiles.get(clientId);
        if (profile != null) {
            profile.setName(profileMap.getOrDefault("name", profile.getName()));
            profile.setContactInfo(profileMap.getOrDefault("contactInfo", profile.getContactInfo()));
            profile.setFrequentFlyerNumber(profileMap.getOrDefault("frequentFlyerNumber", profile.getFrequentFlyerNumber()));
        }
    }

    public void addFrequentFlyerInfo(String clientId, String frequentFlyerNumber) {
        ClientProfile profile = clientProfiles.get(clientId);
        if (profile != null) {
            profile.setFrequentFlyerNumber(frequentFlyerNumber);
        }
    }

    public List<String> getPastBookings(String clientId) {
        List<Booking> bookings = bookingHistory.get(clientId);
        if (bookings != null) {
            return bookings.stream().map(Booking::getBookingNumber).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public void generateSampleData() {
        String[] firstNames = {"John", "Emma", "Michael", "Sophia", "William", "Olivia", "James", "Ava", "Robert", "Isabella"};
        String[] lastNames = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis", "Rodriguez", "Martinez"};
        String[] emailDomains = {"gmail.com", "yahoo.com", "hotmail.com", "outlook.com", "icloud.com"};
        String[] cities = {"New York", "Los Angeles", "Chicago", "Houston", "Phoenix", "Philadelphia", "San Antonio", "San Diego", "Dallas", "San Jose"};
        String[] countries = {"USA", "Canada", "UK", "Australia", "Germany", "France", "Japan", "Brazil", "India", "Italy"};
        String[] seatPreferences = {"Window", "Aisle", "Middle", "No Preference"};
        String[] mealPreferences = {"Regular", "Vegetarian", "Vegan", "Kosher", "Halal", "Gluten-free", "Dairy-free"};

        Random random = new Random();

        for (int i = 1; i <= 50; i++) {
            String clientId = "C" + String.format("%04d", i);
            String firstName = firstNames[random.nextInt(firstNames.length)];
            String lastName = lastNames[random.nextInt(lastNames.length)];
            String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@" + emailDomains[random.nextInt(emailDomains.length)];
            String phone = "+1" + String.format("%10d", ThreadLocalRandom.current().nextLong(1_000_000_000L, 9_999_999_999L));
            
            Map<String, String> preferences = new HashMap<>();
            preferences.put("seatPreference", seatPreferences[random.nextInt(seatPreferences.length)]);
            preferences.put("mealPreference", mealPreferences[random.nextInt(mealPreferences.length)]);
            preferences.put("frequentFlyerNumber", "FF" + String.format("%06d", random.nextInt(1000000)));

            ClientProfile profile = new ClientProfile(clientId, firstName + " " + lastName, email, preferences);
            // Removed setPhone and setAddress calls
            
            createClientProfile(clientId, profile);

            // Generate sample bookings
            generateSampleBookings(clientId, random);

            // Generate sample interactions
            generateSampleInteractions(clientId, random);
        }
    }

    private void generateSampleBookings(String clientId, Random random) {
        int bookingCount = random.nextInt(5) + 1; // 1 to 5 bookings per client
        for (int i = 0; i < bookingCount; i++) {
            LocalDate bookingDate = LocalDate.now().plusDays(random.nextInt(365));
            String bookingNumber = "B" + String.format("%06d", random.nextInt(1000000));
            String destination = "City" + (random.nextInt(20) + 1);
            String[] statuses = {"CONFIRMED", "COMPLETED", "CANCELLED", "AWAITING_CONFIRMATION"};
            String status = statuses[random.nextInt(statuses.length)];
            
            Booking booking = new Booking(bookingNumber, bookingDate, destination, status);
            addBooking(clientId, booking);
        }
    }

    private void generateSampleInteractions(String clientId, Random random) {
        int interactionCount = random.nextInt(3) + 1; // 1 to 3 interactions per client
        for (int i = 0; i < interactionCount; i++) {
            LocalDateTime interactionDate = LocalDateTime.now().minusDays(random.nextInt(30));
            String[] types = {"Email", "Phone", "Chat", "In-person"};
            String type = types[random.nextInt(types.length)];
            String content = "Interaction about " + (random.nextBoolean() ? "booking" : "general inquiry");
            
            Interaction interaction = new Interaction(interactionDate, type, content);
            logInteraction(clientId, interaction);
        }
    }

    public List<ClientProfile> getAllClients() {
        // Implement the logic to return all client profiles
        // This might involve fetching from a database or returning from an in-memory store
        return new ArrayList<>(clientProfiles.values());
    }
}