package com.cosmos.trippr.controller;

import com.cosmos.trippr.entity.*;
import com.cosmos.trippr.enums.SeatType;
import com.cosmos.trippr.repository.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@RestController
public class DataPopulator {


  @Autowired
  private TheaterHallRepository theaterHallRepository;
  @Autowired
  private TheaterHallSeatsRepository theaterHallSeatsRepository;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private MovieRepository movieRepository;
  @Autowired
  private TheaterRepository theaterRepository;
  @Autowired
  private ShowRepository showRepository;

  @GetMapping("/")
  public String home() {
    return "Hey, Welcome to TRIPRRR !!!";
  }

  @GetMapping("/generate")
  public void generateInitialData() {
    log.info("Dummy data generation in progress....");


    // 1. Create TEST user and save to database
    UserEntity testUser = UserEntity.builder()
      .name("Test user")
      .mobile("98176523456")
      .build();

    userRepository.save(testUser);


    // 2. Add Theaters to Database
    TheaterEntity theater1 = createTheater("Lakshmi Theater", 1, "Tavarakere, Banglore");
    TheaterEntity theater2 = createTheater("Bala Ji Theater", 2, "BTM layout, Banglore");


    //3. Create Movies
    MovieEntity movie1 = getMovie("Bahubali", "Biggest Indian Motion Picture", "Hindi", LocalDate.now(), "India", "Action, War, Adventure");
    MovieEntity movie2 = getMovie("3 Idiots", "Movie on Indian Education system", "Hindi", LocalDate.now(), "India", "Comedy, Drama");

    ShowEntity show1 = getShow(1.5, LocalDateTime.now(), LocalDateTime.now().plusHours(3), LocalDateTime.now().minusDays(1), LocalDateTime.now().minusDays(1), theater1, movie1);
    ShowEntity show2 = getShow(1.1, LocalDateTime.now(), LocalDateTime.now().plusHours(3), LocalDateTime.now().minusDays(1), LocalDateTime.now().minusDays(1), theater2, movie2);

    log.info(show1+" and "+show2+" are added to the system");
  }


  public ShowEntity getShow(double rateMultiplier, LocalDateTime startTime, LocalDateTime endTime, LocalDateTime createAt, LocalDateTime updatedAt, TheaterEntity theater, MovieEntity movie) {
    ShowEntity show = ShowEntity.builder().rateMultiplier(rateMultiplier).startTime(startTime).endTime(endTime).createdAt(createAt).updatedAt(updatedAt)
      .movie(movie).theater(theater).theaterHall(theater.getHallList().get(0)).build();

    movie.getShowList().add(show);
    theater.getShowList().add(show);
    theater.getHallList().get(0).getShow().add(show);

    for (ShowSeatsEntity showSeats : show.getShowSeats()) {
      showSeats.setShow(show);
    }

    showRepository.save(show);

    return show;
  }


  public MovieEntity getMovie(String title, String desc, String lang, LocalDate releaseDate, String country, String genre) {
    return MovieEntity.builder().title(title).description(desc).language(lang).country(country).releaseDate(releaseDate).genre(genre).build();
  }

  public TheaterEntity createTheater(String name, int numberOfHalls, String location) {
    TheaterEntity theater =
      TheaterEntity.builder()
        .name(name)
        .numberOfHalls(numberOfHalls)
        .address(location)
        .build();
    System.out.println(theater+" is CREATED!!!!! now will create theater Halls");

    for (int i = 1; i <= numberOfHalls; i++) {
      TheaterHallEntity theaterHall = createTheaterHall("Hall_"+i, 8);
      theater.getHallList().add(theaterHall);
      theaterHall.setTheater(theater);
    }
    theaterRepository.save(theater);

    return theater;
  }

  public TheaterHallEntity createTheaterHall(String name, int numberOfSeats) {
    TheaterHallEntity theaterHall = TheaterHallEntity.builder()
      .name(name)
      .numberOfSeats(numberOfSeats)
      .build();

    System.out.println(theaterHall+" is CREATED!!!!, not will generate hall seats");

    List<TheaterHallSeatsEntity> theaterHallSeatsEntities = getTheaterHallSeats();
    theaterHall.getTheaterHallSeats().addAll(theaterHallSeatsEntities);
    System.out.println("CHECKKKKK : "+theaterHall.getTheaterHallSeats());

    for (TheaterHallSeatsEntity seat : theaterHallSeatsEntities) {
      seat.setTheaterHall(theaterHall);
    }
    theaterHallRepository.save(theaterHall);

    return theaterHall;
  }


  public List<TheaterHallSeatsEntity> getTheaterHallSeats() {

    List<TheaterHallSeatsEntity> seatsList = new ArrayList<>();

    seatsList.add(createTheaterSeat("1A", 300, SeatType.CLASSIC));
    seatsList.add(createTheaterSeat("1B", 300, SeatType.CLASSIC));
    seatsList.add(createTheaterSeat("1C", 300, SeatType.CLASSIC));
    seatsList.add(createTheaterSeat("1D", 300, SeatType.CLASSIC));

    seatsList.add(createTheaterSeat("2A", 500, SeatType.PREMIUM));
    seatsList.add(createTheaterSeat("2B", 500, SeatType.PREMIUM));
    seatsList.add(createTheaterSeat("2C", 500, SeatType.PREMIUM));
    seatsList.add(createTheaterSeat("2D", 500, SeatType.PREMIUM));

    System.out.println("Theater Halll seats created : "+seatsList.toString());

    theaterHallSeatsRepository.saveAll(seatsList);

    return seatsList;
  }


  public TheaterHallSeatsEntity createTheaterSeat(String seatNumber, int price, SeatType seatType) {
    return TheaterHallSeatsEntity.builder()
      .seatNumber(seatNumber)
      .price(price)
      .seatType(seatType)
      .build();
  }

}
