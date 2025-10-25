package service;

import exception.SeatAlreadyBooked;
import exception.ShowNotFound;
import exception.UserNotFound;
import model.*;
import model.enums.BookingStatus;
import model.enums.ShowSeatStatus;
import repository.ShowRepo;
import repository.ShowSeatRepo;
import repository.UserRepo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BookingServiceImpl implements BookingService{


    private UserRepo userRepo;
    private ShowRepo showRepo;
    private ShowSeatRepo showSeatRepo;

    public BookingServiceImpl(UserRepo userRepo,  ShowRepo showRepo, ShowSeatRepo showSeatRepo) {
        this.userRepo = userRepo;
        this.showRepo = showRepo;
        this.showSeatRepo = showSeatRepo;
    }

    @Override
    public Booking createBooking(Long userId, Long showId, List<Seat> seatsToBook) {

        User user = userRepo.findById(userId).orElseThrow(() -> new UserNotFound("User not found"));

        Show show = showRepo.findById(showId).orElseThrow(() -> new ShowNotFound("Show not found"));

        List<ShowSeat> showSeats = new ArrayList<>();
        for (Seat seat : seatsToBook) {

            ShowSeat showSeat =  showSeatRepo.findShowSeatStatus(show.getId(), seat.getId()).orElseThrow(() -> new SeatAlreadyBooked("Seat is already booked"));

            if(showSeat.getStatus() != ShowSeatStatus.AVALIABLE){

                if(showSeat.getStatus() == ShowSeatStatus.BLOCKED ){
                    long diffInMillis = new Date().getTime() - showSeat.getBookedTime().getTime();
                    long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(diffInMillis);
                    if (diffInMinutes < 15) {
                        throw new SeatAlreadyBooked("Seat is already booked " +  seat.getId());
                    }else{
                        showSeat.setStatus(ShowSeatStatus.AVALIABLE);
                    }
                }else{
                    throw new SeatAlreadyBooked("Seat is already booked");
                }
            }
            showSeat.setBookedTime(new Date());
            showSeat.setStatus(ShowSeatStatus.BLOCKED);
            ShowSeat showSeatData =  showSeatRepo.save(showSeat);

            showSeats.add(showSeatData);
        }
        Booking booking = new Booking();
        booking.setShowSeats(showSeats);
        booking.setBookingStatus(BookingStatus.COMPLETED);
        return booking;

    }
}
