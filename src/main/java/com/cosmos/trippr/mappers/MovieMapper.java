package com.cosmos.trippr.mappers;

import com.cosmos.trippr.dto.MovieDTO;
import com.cosmos.trippr.entity.MovieEntity;

public class MovieMapper {

  public static MovieEntity toEntity(MovieDTO movieDTO) {
    return MovieEntity.builder()
      .title(movieDTO.getTitle())
      .description(movieDTO.getDescription())
      .language(movieDTO.getLanguage())
      .releaseDate(movieDTO.getReleaseDate())
      .country(movieDTO.getCountry())
      .genre(movieDTO.getGenre())
      .build();
  }

  public static MovieDTO toDTO(MovieEntity movie) {
    return MovieDTO.builder()
      .id(movie.getId())
      .title(movie.getTitle())
      .description(movie.getDescription())
      .language(movie.getLanguage())
      .releaseDate(movie.getReleaseDate())
      .country(movie.getCountry())
      .genre(movie.getGenre())
      .build();
  }
}
