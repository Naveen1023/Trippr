package com.cosmos.trippr.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TheaterDTO {
  private long id;
  private String name;
  private int numberOfHalls;
  private String address;
  private double longitude;
  private double latitude;
}
