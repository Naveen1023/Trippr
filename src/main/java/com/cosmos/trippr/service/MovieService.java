package com.cosmos.trippr.service;

import com.cosmos.trippr.controller.utils.AddMovieRequest;
import com.cosmos.trippr.dto.MovieDTO;
import com.cosmos.trippr.entity.MovieEntity;
import com.cosmos.trippr.service.implementation.MovieServiceImpl;

import java.util.List;

public interface MovieService {

  public MovieEntity addMovie(AddMovieRequest addMovieRequest);
  List<MovieDTO> getMovieByName(String name);
  MovieDTO getMovieById(long id);
}
