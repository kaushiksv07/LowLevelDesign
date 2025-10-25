package repository.impl;

import model.ShowSeat;
import repository.ShowSeatRepo;

import java.util.Optional;

public class ShowSeatRepoImpl extends BaseRepoImpl<ShowSeat> implements ShowSeatRepo {
    @Override
    public Optional<ShowSeat> findShowSeatStatus(Long showId, Long seatId) {
        return this.findAll().stream()
                .filter(showSeat ->
                        showSeat.getShow().getId().equals(showId) &&
                                showSeat.getSeat().getId().equals(seatId)
                )
                .findFirst();
    }
}
