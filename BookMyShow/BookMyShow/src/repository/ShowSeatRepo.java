package repository;

import model.Show;
import model.ShowSeat;
import model.enums.ShowSeatStatus;

import java.util.Optional;

public interface ShowSeatRepo extends BaseRepo<ShowSeat> {
    Optional<ShowSeat> findShowSeatStatus(Long showId, Long seatId);
}
