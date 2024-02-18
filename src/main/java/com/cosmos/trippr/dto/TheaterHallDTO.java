package com.cosmos.trippr.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TheaterHallDTO {
  private long id;
  private String name;
  private int numberOfSeats;
}

