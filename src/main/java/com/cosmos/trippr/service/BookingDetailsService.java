package com.cosmos.trippr.service;

import com.cosmos.trippr.controller.utils.CreateBookingRequest;
import com.cosmos.trippr.service.pojos.BookTicketResponse;
import org.springframework.stereotype.Service;

@Service
public interface BookingDetailsService {

    public BookTicketResponse bookTicket(CreateBookingRequest bookingRequest);
}
