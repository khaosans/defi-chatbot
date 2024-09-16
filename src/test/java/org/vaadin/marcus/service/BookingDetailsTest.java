package org.vaadin.marcus.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BookingDetailsTest {

    @Test
    void testBookingDetailsConstructorAndGetters() {
        // Arrange
        String bookingNumber = "BK001";
        String firstName = "John";
        String lastName = "Doe";
        String date = "2023-04-15";
        String from = "New York";
        String to = "London";
        String bookingStatus = "Confirmed";
        String bookingClass = "Economy";

        // Act
        BookingDetails bookingDetails = new BookingDetails(bookingNumber, firstName, lastName, date, from, to, bookingStatus, bookingClass);

        // Assert
        assertEquals(bookingNumber, bookingDetails.getBookingNumber());
        assertEquals(firstName, bookingDetails.getFirstName());
        assertEquals(lastName, bookingDetails.getLastName());
        assertEquals(date, bookingDetails.getDate());
        assertEquals(from, bookingDetails.getFrom());
        assertEquals(to, bookingDetails.getTo());
        assertEquals(bookingStatus, bookingDetails.getBookingStatus());
        assertEquals(bookingClass, bookingDetails.getBookingClass());
    }
}