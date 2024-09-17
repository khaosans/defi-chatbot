package org.vaadin.marcus.client;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.stereotype.Service;
import org.vaadin.marcus.data.BookingStatus;
import org.vaadin.marcus.service.BookingDetails;
import org.vaadin.marcus.service.FlightService;

import java.util.List;
import java.util.stream.Collectors;

@AnonymousAllowed
@Service
public class BookingService {
    private final FlightService flightService;

    public BookingService(FlightService flightService) {
        this.flightService = flightService;
    }

    public List<BookingDetails> getBookings() {
        return flightService.getBookings();
    }

    //confirm booking
    public void confirmBooking(String bookingNumber, String firstName, String lastName) {
        flightService.confirmBooking(bookingNumber, firstName, lastName);
    }

    public List<BookingDetails> getConfirmedBookings() {
        return flightService.getBookings().stream()
                .filter(booking -> booking.bookingStatus().equals( BookingStatus.CONFIRMED))
                .collect(Collectors.toList());
    }
}
