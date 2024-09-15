package org.vaadin.marcus.simulation;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.vaadin.marcus.service.*;

import java.util.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class MockDataGenerator {
    private static final Logger logger = LoggerFactory.getLogger(MockDataGenerator.class);
    private final ObjectMapper objectMapper;
    private final Random random = new Random();
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final List<String> cities = List.of("New York", "London", "Paris", "Tokyo", "Sydney", "Dubai", "Singapore", "Hong Kong", "Frankfurt", "Amsterdam");
    private final List<String> statuses = List.of("On Time", "Delayed", "Boarding", "Departed", "Arrived");
    private final List<String> bookingStatuses = List.of("CONFIRMED", "COMPLETED", "CANCELLED", "AWAITING_CONFIRMATION", "AVAILABLE");
    private final List<String> bookingClasses = Arrays.asList("Economy", "Business", "First");
    private final List<String> firstNames = Arrays.asList("James", "Mary", "John", "Patricia", "Robert", "Jennifer", "Michael", "Linda", "William", "Elizabeth");
    private final List<String> lastNames = Arrays.asList("Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis", "Rodriguez", "Martinez");

    public MockDataGenerator(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String generateFlightStatus() {
        try {
            ArrayNode flightStatuses = objectMapper.createArrayNode();
            for (int i = 0; i < 5; i++) {
                flightStatuses.add(createFlightStatus());
            }
            return objectMapper.writeValueAsString(flightStatuses);
        } catch (Exception e) {
            logger.error("Error generating flight statuses", e);
            return "[]";
        }
    }

    public String generateBookings(int count) {
        try {
            ArrayNode bookings = objectMapper.createArrayNode();
            for (int i = 0; i < count; i++) {
                bookings.add(createBooking(i + 1));
            }
            return objectMapper.writeValueAsString(bookings);
        } catch (Exception e) {
            logger.error("Error generating bookings", e);
            return "[]";
        }
    }

    public List<ClientProfile> generateMockClients(int count) {
        List<ClientProfile> clients = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            clients.add(createClientProfile(i + 1));
        }
        return clients;
    }

    private ObjectNode createBooking(int index) {
        ObjectNode booking = objectMapper.createObjectNode();
        String status = bookingStatuses.get(random.nextInt(bookingStatuses.size()));
        booking.put("bookingNumber", "B" + String.format("%03d", index));
        booking.put("firstName", status.equals("AVAILABLE") ? "" : firstNames.get(random.nextInt(firstNames.size())));
        booking.put("lastName", status.equals("AVAILABLE") ? "" : lastNames.get(random.nextInt(lastNames.size())));
        booking.put("date", LocalDate.now().plusDays(random.nextInt(365)).format(dateFormatter));
        booking.put("from", cities.get(random.nextInt(cities.size())));
        booking.put("to", cities.get(random.nextInt(cities.size())));
        booking.put("bookingStatus", status);
        booking.put("bookingClass", bookingClasses.get(random.nextInt(bookingClasses.size())));
        return booking;
    }

    private ClientProfile createClientProfile(int index) {
        String id = "client" + index;
        String name = firstNames.get(random.nextInt(firstNames.size())) + " " + lastNames.get(random.nextInt(lastNames.size()));
        String contactInfo = "client" + index + "@example.com";
        String frequentFlyerNumber = "FF" + (1000 + index);
        int travelScore = index * 10;
        LoyaltyStatus loyaltyStatus = LoyaltyStatus.valueOf(calculateLoyaltyStatus(travelScore));
        String lastTravelDate = LocalDate.now().minusDays(index).format(dateFormatter);
        
        Map<String, String> preferences = new HashMap<>();
        preferences.put("seatPreference", index % 2 == 0 ? "Window" : "Aisle");
        preferences.put("mealPreference", index % 3 == 0 ? "Vegetarian" : "Regular");
        
        return new ClientProfile(id, name, contactInfo, frequentFlyerNumber, loyaltyStatus, travelScore, lastTravelDate, preferences);
    }

    private ObjectNode createFlightStatus() {
        String flightNumber = "FA" + (100 + random.nextInt(900));
        String departure = cities.get(random.nextInt(cities.size()));
        String arrival;
        do {
            arrival = cities.get(random.nextInt(cities.size()));
        } while (arrival.equals(departure));
        String status = statuses.get(random.nextInt(statuses.size()));
        LocalTime departureTime = LocalTime.of(random.nextInt(24), random.nextInt(60));
        LocalTime arrivalTime = departureTime.plusHours(1 + random.nextInt(10));

        ObjectNode flightStatus = objectMapper.createObjectNode();
        flightStatus.put("flightNumber", flightNumber);
        flightStatus.put("departure", departure);
        flightStatus.put("arrival", arrival);
        flightStatus.put("status", status);
        flightStatus.put("departureTime", departureTime.format(timeFormatter));
        flightStatus.put("arrivalTime", arrivalTime.format(timeFormatter));
        return flightStatus;
    }

    private String calculateLoyaltyStatus(int travelScore) {
        if (travelScore >= 100) return "PLATINUM";
        if (travelScore >= 50) return "GOLD";
        if (travelScore >= 20) return "SILVER";
        return "BRONZE";
    }
}