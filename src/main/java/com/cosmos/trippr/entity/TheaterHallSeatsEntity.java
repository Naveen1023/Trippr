package com.cosmos.trippr.entity;

import com.cosmos.trippr.enums.SeatType;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "theaterHall_seats")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class TheaterHallSeatsEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "seat_number", nullable = false)
  private String seatNumber;

  @Column(name = "rate", nullable = false)
  private double price;

  @Column(name = "seat_type", nullable = false)
  private SeatType seatType;

  @ManyToOne
  private TheaterHallEntity theaterHall;
}
