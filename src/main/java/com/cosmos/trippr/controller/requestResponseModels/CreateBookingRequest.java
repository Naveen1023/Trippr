package com.cosmos.trippr.controller.requestResponseModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateBookingRequest {
  private long showId;
  private long userId;
  private List<String> seatNumbers;
}
