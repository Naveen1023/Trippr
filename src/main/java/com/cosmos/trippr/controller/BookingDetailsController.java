package com.cosmos.trippr.controller;


import com.cosmos.trippr.controller.utils.CreateBookingRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("booking")
public class BookingDetailsController {


  @PostMapping("create")
  public ResponseEntity<Object> createBooking(CreateBookingRequest createBookingRequest) {
    return null;
  }

}
