package com.cosmos.trippr.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "theater_halls")
@Entity
public class TheaterHallEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "name", nullable = false)
  private String name;
  @Column(name = "number_of_seats", nullable = false)
  private int numberOfSeats;

  @ManyToOne
  @JoinColumn(name = "theater_id", referencedColumnName="id")
  private TheaterEntity theater;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "theater_hall_id")
  @Builder.Default
  private List<ShowEntity> show= new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "theater_hall_id")
  @Builder.Default
  private List<TheaterHallSeatsEntity> theaterHallSeats = new ArrayList<>();
}
