

import controller.BookingController;
import model.*;
import model.enums.MovieJoner;
import model.enums.ShowSeatStatus;
import repository.*;
import repository.impl.*;
import service.BookingService;
import service.BookingServiceImpl;

import java.util.*;

public class Client {
    public static void main(String[] args) {

        // 1️⃣ Initialize repositories
        UserRepo userRepo = new UserRepoImpl();
        MovieRepo movieRepo = new MovieRepoImpl();
        TheatersRepo theaterRepo = new TheatersRepoImpl();
        CityRepo cityRepo = new CityRepoImpl();
        ScreenRepo screenRepo = new ScreenRepoImpl();
        ShowRepo showRepo = new ShowRepoImpl();
        ShowSeatRepo showSeatRepo = new ShowSeatRepoImpl();
        SeatRepo seatRepo = new SeatRepoImpl();

        // 2️⃣ Create service and controller
        BookingService bookingService = new BookingServiceImpl(userRepo, showRepo, showSeatRepo);
        BookingController bookingController = new BookingController(bookingService);

        // 3️⃣ Setup base data - City
        City city = new City();
        city.setName("Bangalore");
        cityRepo.save(city);

        // 4️⃣ Theater
        Theaters theater = new Theaters();
        theater.setName("PVR Koramangala");
//        theater.setCity(city);
        theaterRepo.save(theater);

        // 5️⃣ Screen
        Screen screen = new Screen();
//        screen.setName("Screen 1");
        screenRepo.save(screen);

        // 6️⃣ Movie
        Movie movie = new Movie();
        movie.setMovieName("Interstellar");
        movie.setMovieJoner(MovieJoner.SUSPENSE);
        movieRepo.save(movie);

        // 7️⃣ Show
        Show show = new Show();
        show.setMovie(movie);
        show.setTime(new Date());
        showRepo.save(show);

        // 8️⃣ Seats
        List<Seat> seats = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Seat seat = new Seat();
            seat.setName("A" + i);
            seatRepo.save(seat);
            seats.add(seat);
        }

        // 9️⃣ Create ShowSeats for this Show
        for (Seat seat : seats) {
            ShowSeat showSeat = new ShowSeat();
            showSeat.setSeat(seat);
            showSeat.setShow(show);
            showSeat.setStatus(ShowSeatStatus.AVALIABLE);
            showSeatRepo.save(showSeat);
        }

        // 🔟 Create a user
        User user = new User();
        user.setUsername("Kaushik");
        userRepo.save(user);

        // 1️⃣1️⃣ Try booking seats
        List<Seat> seatsToBook = Arrays.asList(seats.get(0), seats.get(1));

        Booking booking = bookingController.createBooking(user.getId(), show.getId(), seatsToBook);

        if (booking != null) {
            System.out.println("🎟️ Booking Confirmed for User: " + user.getUsername());
            System.out.println("✅ Seats booked: ");
            for (ShowSeat ss : booking.getShowSeats()) {
                System.out.println(" - " + ss.getSeat().getName());
            }
        } else {
            System.out.println("❌ Booking failed!");
        }
    }
}
