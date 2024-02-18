package com.cosmos.trippr.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ShowSeatsDTO {
  private long id;
  private int seatNo;
  private double price;
  private boolean isBooked;
}

