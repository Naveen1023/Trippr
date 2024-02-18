package com.cosmos.trippr.service.implementation;

import com.cosmos.trippr.controller.utils.AddMovieRequest;
import com.cosmos.trippr.dto.MovieDTO;
import com.cosmos.trippr.entity.MovieEntity;
import com.cosmos.trippr.mappers.MovieMapper;
import com.cosmos.trippr.repository.MovieRepository;
import com.cosmos.trippr.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

  @Autowired
  private MovieRepository movieRepository;

  @Override
  public MovieEntity addMovie(AddMovieRequest addMovieRequest) {
    MovieEntity movie = MovieEntity.builder()
      .title(addMovieRequest.getTitle())
      .description(addMovieRequest.getDescription())
      .language(addMovieRequest.getLanguage())
      .releaseDate(addMovieRequest.getReleaseDate())
      .country(addMovieRequest.getCountry())
      .genre(addMovieRequest.getGenre())
      .build();

    return movieRepository.save(movie);
  }

  @Override
  public List<MovieDTO> getMovieByName(String name) {
    List<MovieEntity> movies = movieRepository.findByTitleContaining(name);
    List<MovieDTO> mappedMovies = new ArrayList<>();

    for(MovieEntity movie : movies) mappedMovies.add(MovieMapper.toDTO(movie));

    return mappedMovies;
  }

  @Override
  public MovieDTO getMovieById(long id) {
    Optional<MovieEntity> movie = movieRepository.findById(id);
    if(!movie.isPresent()) throw new RuntimeException("Movie Not Found!!!");
    return MovieMapper.toDTO(movie.get());
  }
}
