package com.cosmos.trippr.controller;

import com.cosmos.trippr.controller.requestResponseModels.AddMovieRequest;
import com.cosmos.trippr.controller.requestResponseModels.ResponseBase;
import com.cosmos.trippr.dto.MovieDTO;
import com.cosmos.trippr.entity.MovieEntity;
import com.cosmos.trippr.service.MovieService;
import com.cosmos.trippr.utils.cache.redis.RedisService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("movie")
public class MovieController {

  @Autowired
  private MovieService movieService;
  @Autowired
  private RedisService redisService;

  @PostMapping("/add")
  public ResponseEntity<Object> addMovie(@RequestBody AddMovieRequest addMovieRequest) {
    try {
      log.info("Received request to delete a TheaterEntity : " + addMovieRequest.getTitle());
      MovieEntity movieResponse = movieService.addMovie(addMovieRequest);
      return ResponseBase.generateResponse(HttpStatus.OK, movieResponse, "Successfully Added");
    } catch (Exception e) {
      return ResponseBase.generateResponse(HttpStatus.UNPROCESSABLE_ENTITY, null, e.getMessage());
    }
  }

  @GetMapping()
  public ResponseEntity<Object> getMovieByName(@RequestParam String name) {
    try {
      log.info("Received request get movies for name : " + name);
      List<MovieDTO> movies = movieService.getMovieByName(name);
      return ResponseBase.generateResponse(HttpStatus.OK, movies, "Successfully Fetched");
    } catch (Exception e) {
      return ResponseBase.generateResponse(HttpStatus.UNPROCESSABLE_ENTITY, null, e.getMessage());
    }
  }

  @GetMapping("{id}")
  public ResponseEntity<Object> getMovieById(@PathVariable long id) {
    try {
      long startTime = System.nanoTime();
      log.info("Received request get movies for id : " + id);
      MovieDTO res = (MovieDTO) redisService.getFromRedis("MOVIE_" + id);
      if (res == null) {
        res = movieService.getMovieById(id);
        redisService.saveToRedis("MOVIE_" + id, res);
      }
      long endTime = System.nanoTime(); // Capture end timestamp
      long elapsedTime = endTime - startTime; // Calculate elapsed time
      System.out.println("Execution time: " + elapsedTime + " nanoseconds");
      return ResponseBase.generateResponse(HttpStatus.OK, res, "Successfully Fetched");
    } catch (Exception e) {
      return ResponseBase.generateResponse(HttpStatus.UNPROCESSABLE_ENTITY, null, e.getMessage());
    }
  }

}
