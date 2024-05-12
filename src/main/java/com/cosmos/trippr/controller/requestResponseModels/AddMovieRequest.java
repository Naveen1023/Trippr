package com.cosmos.trippr.controller.requestResponseModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddMovieRequest {
  private String title;
  private String description;
  private String language;
  private LocalDate releaseDate;
  private String country;
  private String genre;
}
