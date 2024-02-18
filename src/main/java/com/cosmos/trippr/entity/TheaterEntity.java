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
@Table(name = "theater")
@Entity
public class TheaterEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "number_of_halls", nullable = false)
  private int numberOfHalls;

  @Column(name = "address", nullable = false)
  private String address;

  @Column(name = "longitude", nullable = false)
  private double longitude;

  @Column(name = "latitude", nullable = false)
  private double latitude;

  // One theater can have multiple theater_halls
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "theater")
  @Builder.Default
  List<TheaterHallEntity> hallList = new ArrayList<>();

  // one theater can have multiple shows running
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "theater_id")
  @Builder.Default
  List<ShowEntity> showList = new ArrayList<>();
}

