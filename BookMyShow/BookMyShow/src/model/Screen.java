package model;

import java.util.List;

public class Screen extends BaseModel{
    private List<Show> shows;
    private List<Seat> seats;

    public List<Show> getShows() {
        return shows;
    }

    public void setShows(List<Show> shows) {
        this.shows = shows;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}
