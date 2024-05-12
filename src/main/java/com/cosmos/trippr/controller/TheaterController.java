package com.cosmos.trippr.controller;

import com.cosmos.trippr.controller.requestResponseModels.ResponseBase;
import com.cosmos.trippr.dto.TheaterDTO;
import com.cosmos.trippr.service.TheaterService;
import com.cosmos.trippr.service.pojos.AddTheaterResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("theater")
public class TheaterController {

  @Autowired
  private TheaterService theaterService;

  @PostMapping("add")
  public ResponseEntity<Object> addTheater(@RequestBody TheaterDTO theaterDTO) {
    try {
      log.info("Received request to add a new TheaterEntity : " + theaterDTO.getName());
      AddTheaterResponse theaterResponse = theaterService.addTheater(theaterDTO);

      return ResponseBase.generateResponse(HttpStatus.OK, theaterResponse, "Successfully Added");
    } catch (Exception e) {
      return ResponseBase.generateResponse(HttpStatus.UNPROCESSABLE_ENTITY, null, e.getMessage());
    }
  }
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Object> deleteTheater(@PathVariable long id) {
    try {
      log.info("Received request to delete a TheaterEntity : " + id);
      theaterService.deleteTheater(id);
      return ResponseBase.generateResponse(HttpStatus.OK, null, "Successfully Deleted");
    } catch (Exception e) {
      return ResponseBase.generateResponse(HttpStatus.UNPROCESSABLE_ENTITY, null, e.getMessage());
    }
  }

  @GetMapping("{id}")
  public ResponseEntity<Object> getTheater(@PathVariable long id) {
    try {
      log.info("Received request to get a TheaterEntity : " + id);
      TheaterDTO theater = theaterService.getTheater(id);
      return ResponseBase.generateResponse(HttpStatus.OK, theater, "Successfully fetched");
    } catch (Exception e) {
      return ResponseBase.generateResponse(HttpStatus.UNPROCESSABLE_ENTITY, null, e.getMessage());
    }
  }
}
