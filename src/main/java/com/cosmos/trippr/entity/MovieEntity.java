package com.cosmos.trippr.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "movies")
@Entity
public class MovieEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "description", nullable = false)
  private String description;

  @Column(name = "language", nullable = false)
  private String language;

  @Column(name = "release_date", nullable = false, columnDefinition = "DATE")
  private LocalDate releaseDate;

  @Column(name = "country", nullable = false)
  private String country;

  @Column(name = "genre")
  private String genre;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "movie_id")
  @Builder.Default
  List<ShowEntity> showList = new ArrayList<>();

}
