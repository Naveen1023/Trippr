package com.cosmos.trippr.mappers;

import com.cosmos.trippr.controller.utils.GetShowsByMovieNameResponse;
import com.cosmos.trippr.dto.MovieDTO;
import com.cosmos.trippr.dto.ShowDTO;
import com.cosmos.trippr.dto.TheaterDTO;
import com.cosmos.trippr.entity.ShowEntity;

public class ShowMapper {

  public static ShowEntity toEntity(ShowDTO showDTO) {
    return ShowEntity.builder()
      .rateMultiplier(showDTO.getRateMultiplier())
      .startTime(showDTO.getStartTime())
      .endTime(showDTO.getEndTime())
      .createdAt(showDTO.getCreatedAt())
      .updatedAt(showDTO.getUpdatedAt())
      .build();
  }

  public static ShowDTO toDTO(ShowEntity show) {
    return ShowDTO.builder()
      .id(show.getId())
      .rateMultiplier(show.getRateMultiplier())
      .startTime(show.getStartTime())
      .endTime(show.getEndTime())
      .createdAt(show.getCreatedAt())
      .updatedAt(show.getUpdatedAt())
      .build();
  }

  public static GetShowsByMovieNameResponse toGetShowByMovieNameResponse(ShowEntity show) {

    TheaterDTO theater = TheaterMapper.toDTO(show.getTheater());
    MovieDTO movie = MovieMapper.toDTO(show.getMovie());

    return GetShowsByMovieNameResponse.builder()
            .id(show.getId())
            .rateMultiplier(show.getRateMultiplier())
            .startTime(show.getStartTime())
            .endTime(show.getEndTime())
            .theater(theater)
            .movie(movie)
            .build();
  }
}
