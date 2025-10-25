package model;

import model.enums.BookingStatus;

import java.util.List;

public class Booking extends BaseModel{
    private List<ShowSeat> showSeats;
    private BookingStatus bookingStatus;

    public List<ShowSeat> getShowSeats() {
        return showSeats;
    }

    public void setShowSeats(List<ShowSeat> showSeats) {
        this.showSeats = showSeats;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}
