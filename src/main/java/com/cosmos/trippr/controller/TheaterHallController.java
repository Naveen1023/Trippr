package com.cosmos.trippr.controller;

import com.cosmos.trippr.controller.requestResponseModels.AddTheaterHallRequest;
import com.cosmos.trippr.controller.requestResponseModels.ResponseBase;
import com.cosmos.trippr.service.TheaterHallService;
import com.cosmos.trippr.service.pojos.AddTheaterHallResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("theaterHall")
public class TheaterHallController {

  @Autowired
  private TheaterHallService theaterHallService;

  @PostMapping("add")
  public ResponseEntity<Object> addTheaterHall(@RequestBody AddTheaterHallRequest addTheaterHallRequest) {
    try {
      log.info("Request for adding a new TheaterHall is received :  " + addTheaterHallRequest.getName());
      AddTheaterHallResponse addTheaterHallResponse = theaterHallService.addTheaterHall(addTheaterHallRequest);

      return ResponseBase.generateResponse(HttpStatus.OK, addTheaterHallResponse, "Successfully Added");
    } catch (Exception e) {
      return ResponseBase.generateResponse(HttpStatus.UNPROCESSABLE_ENTITY, null, e.getMessage());
    }
  }

  @DeleteMapping("delete/{id}")
  public ResponseEntity<Object> deleteTheaterHall(@PathVariable long id) {
    try {
      log.info("Received request to delete a TheaterHallEntity : " + id);
      theaterHallService.deleteTheater(id);
      return ResponseBase.generateResponse(HttpStatus.OK, null, "Successfully DELETED");
    } catch (Exception e) {
      return ResponseBase.generateResponse(HttpStatus.UNPROCESSABLE_ENTITY, null, e.getMessage());
    }
  }
}
