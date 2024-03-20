package com.cosmos.trippr.repository;

import com.cosmos.trippr.entity.ShowSeatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShowSeatsRepository extends JpaRepository<ShowSeatsEntity, Long> {

    @Query(value = "SELECT * FROM show_seats WHERE show_id = (?1) and seat_no IN (?2) and is_booked = false", nativeQuery = true)
    public List<ShowSeatsEntity> findAvailableShowSeats(long showId, List<String> seatNumbers);
}
