package com.cosmos.trippr.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class BookingDetailsDTO {
  private long id;
  private String bookingNo;
  private String bookingStatus;
  private LocalDateTime createdAt;
}