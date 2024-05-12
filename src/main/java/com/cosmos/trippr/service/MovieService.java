package com.cosmos.trippr.service;

import com.cosmos.trippr.controller.requestResponseModels.AddMovieRequest;
import com.cosmos.trippr.dto.MovieDTO;
import com.cosmos.trippr.entity.MovieEntity;

import java.util.List;

public interface MovieService {

  public MovieEntity addMovie(AddMovieRequest addMovieRequest);
  List<MovieDTO> getMovieByName(String name);
  MovieDTO getMovieById(long id);
}
