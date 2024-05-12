package com.cosmos.trippr.controller.requestResponseModels;

import com.cosmos.trippr.dto.ShowDTO;
import com.cosmos.trippr.dto.TheaterDTO;
import lombok.*;

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
