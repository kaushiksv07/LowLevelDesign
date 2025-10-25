package controller;

import model.Booking;
import model.Seat;
import service.BookingService;

import java.util.List;

public class BookingController {

    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public Booking createBooking(Long userId, Long showId, List<Seat> seatsToBook) {
        try {
            Booking booking = bookingService.createBooking(userId, showId, seatsToBook);
            System.out.println("✅ Booking successful for user: " + userId);
            return booking;
        } catch (Exception e) {
            System.out.println("❌ Booking failed: " + e.getMessage());
            return null;
        }
    }
}
