package com.cosmos.trippr.entity;

import com.cosmos.trippr.enums.SeatType;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "show_seats")
@Entity
public class ShowSeatsEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "seat_no", nullable = false)
  private String seatNo;
  @Column(name = "price", nullable = false)
  private double price;
  @Column(name = "seat_type")
  private SeatType seatType;
  @Column(name = "is_booked", nullable = false)
  private boolean isBooked;

  @ManyToOne
  private ShowEntity show;

  @ManyToOne(cascade = CascadeType.ALL)
  private BookingDetailsEntity booking;
}
