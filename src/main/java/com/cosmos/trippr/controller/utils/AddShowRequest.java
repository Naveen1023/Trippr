package com.cosmos.trippr.controller.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddShowRequest {
  private double rateMultiplier;
  private LocalDateTime startTime;
  private LocalDateTime endTime;
  private long movieId;
  private long theaterHallId;
  private long theaterId;
}