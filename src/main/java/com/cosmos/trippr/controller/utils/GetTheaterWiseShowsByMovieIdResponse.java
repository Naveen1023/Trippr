package com.cosmos.trippr.controller.utils;

import com.cosmos.trippr.dto.ShowDTO;
import com.cosmos.trippr.dto.TheaterDTO;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class GetTheaterWiseShowsByMovieIdResponse {
    private List<TheaterShow> theaterShows;

    @AllArgsConstructor
    @Getter
    @Setter
    @Builder
    public static class TheaterShow {
        private TheaterDTO theater;
        private List<ShowDTO> shows;
    }
}
