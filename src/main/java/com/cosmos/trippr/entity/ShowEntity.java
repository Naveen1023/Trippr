package com.cosmos.trippr.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
@Table(name = "shows")
@Entity
public class ShowEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "rate_multiplier")
  private double rateMultiplier = 1.0;
  @Column(name = "start_time", columnDefinition = "TIMESTAMP")
  private LocalDateTime startTime;

  @Column(name = "end_time", columnDefinition = "TIMESTAMP")
  private LocalDateTime endTime;

  @Column(name = "created_at", columnDefinition = "TIMESTAMP")
  private LocalDateTime createdAt;

  @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
  private LocalDateTime updatedAt;

  // one show can have multiple bookings
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "show_id")
  @Builder.Default
  List<BookingDetailsEntity> bookings = new ArrayList<>();

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "show_id")
  @Builder.Default
  List<ShowSeatsEntity> showSeats = new ArrayList<>();

  @ManyToOne
  private MovieEntity movie;

  @ManyToOne
  private TheaterEntity theater;

  @ManyToOne
  private TheaterHallEntity theaterHall;
}
