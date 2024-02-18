package com.cosmos.trippr.dto;

import lombok.*;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ShowDTO {
  private long id;
  private double rateMultiplier = 1.0;
  private LocalDateTime startTime;
  private LocalDateTime endTime;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
