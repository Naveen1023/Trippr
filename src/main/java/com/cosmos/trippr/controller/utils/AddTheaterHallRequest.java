package com.cosmos.trippr.controller.utils;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddTheaterHallRequest {
  private String name;
  private int numberOfSeats;
  private long theaterId;
}

