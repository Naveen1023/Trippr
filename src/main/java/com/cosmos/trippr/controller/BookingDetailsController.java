package com.cosmos.trippr.controller;


import com.cosmos.trippr.controller.utils.CreateBookingRequest;
import com.cosmos.trippr.controller.utils.ResponseBase;
import com.cosmos.trippr.entity.MovieEntity;
import com.cosmos.trippr.service.BookingDetailsService;
import com.cosmos.trippr.service.pojos.BookTicketResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("booking")
public class BookingDetailsController {

  @Autowired
  private BookingDetailsService bookingDetailsService;

  @PostMapping("create")
  public ResponseEntity<Object> createBooking(@RequestBody CreateBookingRequest bookingRequest) {
    try {
      log.info("Received request to Create Booking for userId : " + bookingRequest.getUserId() + " & showId : " + bookingRequest.getShowId());

      BookTicketResponse ticketResponse = bookingDetailsService.bookTicket(bookingRequest);

      return ResponseBase.generateResponse(HttpStatus.OK, ticketResponse, "Successfully Booked");
    } catch (Exception e) {
      return ResponseBase.generateResponse(HttpStatus.UNPROCESSABLE_ENTITY, null, e.getMessage());
    }
  }
}
