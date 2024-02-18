package com.cosmos.trippr.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class MovieDTO {
  private long id;
  private String title;
  private String description;
  private String language;
  private LocalDate releaseDate;
  private String country;
  private String genre;
}
