package com.cosmos.trippr.controller;

import com.cosmos.trippr.controller.requestResponseModels.AddShowRequest;
import com.cosmos.trippr.controller.requestResponseModels.GetShowsByMovieNameResponse;
import com.cosmos.trippr.controller.requestResponseModels.GetTheaterWiseShowsByMovieIdResponse;
import com.cosmos.trippr.controller.requestResponseModels.ResponseBase;
import com.cosmos.trippr.entity.ShowEntity;
import com.cosmos.trippr.service.ShowService;
import com.cosmos.trippr.service.pojos.GetShowDetailsByIdResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("show")
public class ShowController {

  @Autowired
  private ShowService showService;

  @PostMapping("add")
  public ResponseEntity<Object> addShow(@RequestBody AddShowRequest addShowRequest) {
    try {
      log.info("Request for adding a show is received : " + addShowRequest);
      ShowEntity showResponse = showService.addShow(addShowRequest);
      return ResponseBase.generateResponse(HttpStatus.OK, "CREATED", "Successfully Added");
    } catch (Exception e) {
      return ResponseBase.generateResponse(HttpStatus.UNPROCESSABLE_ENTITY, null, e.getMessage());
    }
  }

  @DeleteMapping("delete/{id}")
  public ResponseEntity<Object> deleteShow(@PathVariable long id) {
    try {
      log.info("Request for deleting a show is received : ");
      showService.deleteShow(id);
      return ResponseBase.generateResponse(HttpStatus.OK, null, "Successfully DLEETED");
    } catch (Exception e) {
      return ResponseBase.generateResponse(HttpStatus.UNPROCESSABLE_ENTITY, null, e.getMessage());
    }
  }

  @GetMapping("theater/{id}/all")
  public ResponseEntity<Object> getShowsByTheater(@PathVariable long id) {
    try {
      log.info("Request for getting all show for theaterID : " + id + " is RECEIVED.");
      List<ShowEntity> shows = showService.getShowsByTheaterId(id);

      for (ShowEntity show : shows) {
        log.info("show movie-> " + show.getMovie().getTitle() + " ,showHall -> " + show.getTheaterHall().getName());
      }

      return ResponseBase.generateResponse(HttpStatus.OK, "ok", "Successfully Fetched");
    } catch (Exception e) {
      return ResponseBase.generateResponse(HttpStatus.UNPROCESSABLE_ENTITY, null, e.getMessage());
    }
  }

  @GetMapping("movie/all")
  public ResponseEntity<Object> getShowsByMovieName(@RequestParam String movieName) {
    try {
      log.info("Request all shows for movie  : " + movieName + " is RECEIVED.");
      List<GetShowsByMovieNameResponse> shows = showService.getByMovieName(movieName);
      return ResponseBase.generateResponse(HttpStatus.OK, shows, "Successfully Fetched");
    } catch (Exception e) {
      return ResponseBase.generateResponse(HttpStatus.UNPROCESSABLE_ENTITY, null, e.getMessage());
    }
  }

  @GetMapping("/movies/all/{id}")
  public ResponseEntity<Object> getShowsByMovieId(@PathVariable long id ) {
    try {
      log.info("Request all shows(TheaterWise) for movie  : " + id + " is RECEIVED.");
      GetTheaterWiseShowsByMovieIdResponse theaterWiseShows = showService.getTheaterWiseShowByMovieId(id);
      return ResponseBase.generateResponse(HttpStatus.OK, theaterWiseShows, "Successfully Fetched");
    } catch (Exception e) {
      return ResponseBase.generateResponse(HttpStatus.UNPROCESSABLE_ENTITY, null, e.getMessage());
    }
  }


  @GetMapping("{id}")
  public ResponseEntity<Object> getShowsById(@PathVariable long id ) {
    try {
      log.info("Request to get a show by Id  : " + id + " is RECEIVED.");
      GetShowDetailsByIdResponse showResponse = showService.getShowById(id);
      return ResponseBase.generateResponse(HttpStatus.OK, showResponse, "Successfully Fetched");
    } catch (Exception e) {
      return ResponseBase.generateResponse(HttpStatus.UNPROCESSABLE_ENTITY, null, e.getMessage());
    }
  }
}
