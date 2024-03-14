package com.cosmos.trippr.dto;

import com.cosmos.trippr.enums.SeatType;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ShowSeatsDTO {
  private long id;
  private String seatNo;
  private double price;
  private boolean isBooked;
  private String seatType;
}

