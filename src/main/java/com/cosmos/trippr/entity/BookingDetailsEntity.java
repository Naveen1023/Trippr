package com.cosmos.trippr.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "booking_details")
@Entity
public class BookingDetailsEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "booking_number", nullable = false)
  private String bookingNo;
  @Column(name = "number_of_seats", nullable = false)
  private int numberOfSeats;
  @Column(name = "booking_status")
  private String bookingStatus;
  @Column(name = "created_at")
  private LocalDateTime createdAt;
  @Column(name = "longitude")
  private double longitude;
  @Column(name = "latitude")
  private double latitude;

  @ManyToOne
  private UserEntity user;

  @ManyToOne
  private ShowEntity show;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "booking_id")
  @Builder.Default
  private List<ShowSeatsEntity> showSeats = new ArrayList<>();

}
