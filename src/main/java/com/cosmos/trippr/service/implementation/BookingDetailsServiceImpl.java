package com.cosmos.trippr.service.implementation;

import com.cosmos.trippr.controller.utils.CreateBookingRequest;
import com.cosmos.trippr.entity.BookingDetailsEntity;
import com.cosmos.trippr.entity.ShowEntity;
import com.cosmos.trippr.entity.ShowSeatsEntity;
import com.cosmos.trippr.entity.UserEntity;
import com.cosmos.trippr.repository.BookingDetailsRepository;
import com.cosmos.trippr.repository.ShowRepository;
import com.cosmos.trippr.repository.ShowSeatsRepository;
import com.cosmos.trippr.repository.UserRepository;
import com.cosmos.trippr.service.BookingDetailsService;
import com.cosmos.trippr.service.implementation.enums.BookingStatusEnum;
import com.cosmos.trippr.service.pojos.BookTicketResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Log4j2
public class BookingDetailsServiceImpl implements BookingDetailsService {

  @Autowired
  private ShowRepository showRepo;
  @Autowired
  private UserRepository userRepo;
  @Autowired
  private ShowSeatsRepository showSeatsRepo;
  @Autowired
  private BookingDetailsRepository bookingDetailsRepo;

  @Override
  @Transactional
  public BookTicketResponse bookTicket(CreateBookingRequest bookingRequest) {
    Optional<ShowEntity> showOptional = showRepo.findById(bookingRequest.getShowId());
    if (!showOptional.isPresent()) throw new IllegalArgumentException("Show Not Found");

    Optional<UserEntity> userOptional = userRepo.findById(bookingRequest.getUserId());
    if (!userOptional.isPresent()) throw new IllegalArgumentException("User Not Found");

    List<ShowSeatsEntity> listOfAvailableSeats = showSeatsRepo.findAvailableShowSeats(bookingRequest.getShowId(), bookingRequest.getSeatNumbers());

    for (ShowSeatsEntity showSeat : listOfAvailableSeats) {
      log.info("showSeat : " + showSeat.getSeatNo() + " is_booked : " + showSeat.isBooked());
    }

    log.info("requested seats size: " + bookingRequest.getSeatNumbers().size());
    log.info("available seats size: " + listOfAvailableSeats.size());

    if (listOfAvailableSeats.size() != bookingRequest.getSeatNumbers().size())
      throw new RuntimeException("All the seats are not available");

    // data is correct, we can make changes....
    for (ShowSeatsEntity showSeat : listOfAvailableSeats) {
      showSeat.setBooked(true);
      showSeatsRepo.save(showSeat);
    }

    BookingDetailsEntity bookingDetails = BookingDetailsEntity.builder()
      .bookingNo(UUID.randomUUID().toString())
      .showSeats(listOfAvailableSeats)
      .bookingStatus(BookingStatusEnum.PENDING.name())
      .numberOfSeats(listOfAvailableSeats.size())
      .show(showOptional.get())
      .latitude(12.91371)
      .longitude(12.91371)
      .user(userOptional.get())
      .createdAt(LocalDateTime.now())
      .build();
    BookingDetailsEntity savedBookingDetails = bookingDetailsRepo.save(bookingDetails);

    return BookTicketResponse.toResponse(savedBookingDetails);
  }
}
