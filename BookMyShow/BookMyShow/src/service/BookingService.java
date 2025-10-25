package service;

import model.*;

import java.util.List;

public interface BookingService {

    Booking createBooking(Long userId, Long showId, List<Seat> seatsToBook);
}
