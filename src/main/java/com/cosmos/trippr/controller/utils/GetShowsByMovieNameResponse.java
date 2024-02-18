package com.cosmos.trippr.controller.utils;

import com.cosmos.trippr.dto.MovieDTO;
import com.cosmos.trippr.dto.TheaterDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetShowsByMovieNameResponse {
    private long id;
    private double rateMultiplier;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private TheaterDTO theater;
    private MovieDTO movie;
}
