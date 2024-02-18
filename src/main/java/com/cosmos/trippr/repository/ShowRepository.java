package com.cosmos.trippr.repository;

import com.cosmos.trippr.entity.MovieEntity;
import com.cosmos.trippr.entity.ShowEntity;
import com.cosmos.trippr.entity.TheaterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowRepository extends JpaRepository<ShowEntity, Long> {
  public List<ShowEntity> findByTheater(TheaterEntity theater);

  public List<ShowEntity> findByMovie(MovieEntity movie);

}
