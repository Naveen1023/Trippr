package com.cosmos.trippr.service.implementation;

import com.cosmos.trippr.controller.utils.AddTheaterHallRequest;
import com.cosmos.trippr.entity.TheaterEntity;
import com.cosmos.trippr.entity.TheaterHallEntity;
import com.cosmos.trippr.entity.TheaterHallSeatsEntity;
import com.cosmos.trippr.enums.SeatType;
import com.cosmos.trippr.repository.TheaterHallRepository;
import com.cosmos.trippr.repository.TheaterHallSeatsRepository;
import com.cosmos.trippr.repository.TheaterRepository;
import com.cosmos.trippr.service.TheaterHallService;
import com.cosmos.trippr.service.pojos.AddTheaterHallResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class TheaterHallServiceImpl implements TheaterHallService {

  @Autowired
  private TheaterHallRepository theaterHallRepository;
  @Autowired
  private TheaterRepository theaterRepository;
  @Autowired
  private TheaterHallSeatsRepository theaterHallSeatsRepository;

  @Override
  public AddTheaterHallResponse addTheaterHall(AddTheaterHallRequest addTheaterHallRequest) {
    /*
     * 1. Get the Theater.
     * 2. Create TheaterHall and set the corresponding theater
     * 3. Generate Seats for TheaterHall, set TheaterHall for every seat
     * 4. Save TheaterHall
     * 5. Add theaterHall in HallList of the theater, sve the theater too.
     * 6. return ID of newly created hall.
     * */

    TheaterEntity theater = theaterRepository.findById(addTheaterHallRequest.getTheaterId()).get();

    if (theater.getHallList().size() == theater.getNumberOfHalls()) {
      throw new RuntimeException("Maximum Hall capacity Reached");
    }

    TheaterHallEntity theaterHall = TheaterHallEntity.builder()
      .numberOfSeats(addTheaterHallRequest.getNumberOfSeats())
      .name(addTheaterHallRequest.getName())
      .build();
    theaterHall.setTheater(theater);

    List<TheaterHallSeatsEntity> listOfSeats = getTheaterHallSeats(addTheaterHallRequest.getNumberOfSeats());
    for (TheaterHallSeatsEntity seat : listOfSeats) {
      seat.setTheaterHall(theaterHall);
    }

    TheaterHallEntity savedTheaterHall = theaterHallRepository.save(theaterHall);
    theater.getHallList().add(savedTheaterHall);
    theaterRepository.save(theater);

    return new AddTheaterHallResponse(savedTheaterHall.getId());
  }

  @Override
  public void deleteTheater(long id) {
    TheaterHallEntity hall = theaterHallRepository.findById(id).get();
    TheaterEntity theater = hall.getTheater();
    theater.getHallList().remove(hall);

    theaterRepository.save(theater);

    theaterHallRepository.delete(hall);
  }

  public List<TheaterHallSeatsEntity> getTheaterHallSeats(int numberOfSeats) {

    List<TheaterHallSeatsEntity> listOfSeats = new ArrayList<>();

    // 20% seats will be PREMIUM seats, remaining CLASSIC
    int countOfPremiumSeats = (int) Math.ceil(0.2 * numberOfSeats);
    int countOfClassicSeats = numberOfSeats - countOfPremiumSeats;

    // generate CLASSIC seats
    for (int i = 0; i < countOfClassicSeats; i++) {
      TheaterHallSeatsEntity seat = createTheaterHallSeat("C_" + i, SeatType.CLASSIC, SeatType.CLASSIC.getSeatPrice());
      TheaterHallSeatsEntity savedSeat = theaterHallSeatsRepository.save(seat);
      listOfSeats.add(savedSeat);
    }

    // generate PREMIUM seats
    for (int i = 0; i < countOfPremiumSeats; i++) {
      TheaterHallSeatsEntity seat = createTheaterHallSeat("P_" + i, SeatType.PREMIUM, SeatType.PREMIUM.getSeatPrice());
      TheaterHallSeatsEntity savedSeat = theaterHallSeatsRepository.save(seat);
      listOfSeats.add(savedSeat);
    }

    return listOfSeats;
  }

  public TheaterHallSeatsEntity createTheaterHallSeat(String name, SeatType seatType, double price) {
    return TheaterHallSeatsEntity.builder()
      .seatNumber(name)
      .seatType(seatType)
      .price(price)
      .build();
  }
}
