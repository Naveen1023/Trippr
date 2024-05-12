package com.cosmos.trippr.controller.requestResponseModels;

import com.cosmos.trippr.dto.MovieDTO;
import com.cosmos.trippr.dto.ShowSeatsDTO;
import com.cosmos.trippr.dto.TheaterHallDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetShowDetails {
    private long id;
    private double rateMultiplier;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private MovieDTO movie;
    private TheaterHallDTO theaterHall;
    private ShowSeatsDTO showSeats;
}
