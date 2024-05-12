package com.cosmos.trippr.service.implementation;

import com.cosmos.trippr.controller.requestResponseModels.AddShowRequest;
import com.cosmos.trippr.controller.requestResponseModels.GetShowsByMovieNameResponse;
import com.cosmos.trippr.controller.requestResponseModels.GetTheaterWiseShowsByMovieIdResponse;
import com.cosmos.trippr.dto.ShowDTO;
import com.cosmos.trippr.dto.ShowSeatsDTO;
import com.cosmos.trippr.dto.TheaterDTO;
import com.cosmos.trippr.entity.*;
import com.cosmos.trippr.mappers.ShowMapper;
import com.cosmos.trippr.mappers.TheaterMapper;
import com.cosmos.trippr.repository.*;
import com.cosmos.trippr.service.ShowService;
import com.cosmos.trippr.service.pojos.GetShowDetailsByIdResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Log4j2
public class ShowServiceImpl implements ShowService {

  @Autowired
  private ShowRepository showRepository;
  @Autowired
  private TheaterRepository theaterRepository;
  @Autowired
  private TheaterHallRepository theaterHallRepository;
  @Autowired
  private MovieRepository movieRepository;
  @Autowired
  private ShowSeatsRepository showSeatsRepository;

  @Override
  public ShowEntity addShow(AddShowRequest addShowRequest) throws Exception {
    TheaterEntity theater = theaterRepository.findById(addShowRequest.getTheaterId())
      .orElseThrow(() -> new Exception("Theater  not found"));

    TheaterHallEntity theaterHall = theaterHallRepository.findById(addShowRequest.getTheaterHallId())
      .orElseThrow(() -> new Exception("Theater Hall is not Found"));

    MovieEntity movie = movieRepository.findById(addShowRequest.getMovieId())
      .orElseThrow(() -> new Exception("Movie is not Found"));

    log.info("Theater : " + theater.getName() + " TheatreHall : " + theaterHall.getName() + " Movie: " + movie.getTitle());

    //build basic show
    ShowEntity show = ShowEntity.builder()
      .rateMultiplier(addShowRequest.getRateMultiplier())
      .startTime(addShowRequest.getStartTime())
      .endTime(addShowRequest.getEndTime())
      .createdAt(LocalDateTime.now())
      .updatedAt(LocalDateTime.now())
      .build();

    // set initial show seats
    List<ShowSeatsEntity> showSeats = getShowSeats(theaterHall, show.getRateMultiplier());

    //save everything
    show.setShowSeats(showSeats);
    show.setMovie(movie);
    show.setTheaterHall(theaterHall);
    show.setTheater(theater);

    return showRepository.save(show);
  }

  @Override
  public void deleteShow(long id) {
    showRepository.deleteById(id);
  }

  @Override
  public List<ShowEntity> getShowsByTheaterId(long id) {
    TheaterEntity theater = theaterRepository.findById(id).get();
    return showRepository.findByTheater(theater);
  }

  @Override
  public List<GetShowsByMovieNameResponse> getByMovieName(String movieName) {
    List<MovieEntity> movies = movieRepository.findByTitleContaining(movieName);
    List<ShowEntity> shows = new ArrayList<>();
    for (MovieEntity movie : movies) {
      shows.addAll(showRepository.findByMovie(movie));
    }

    List<GetShowsByMovieNameResponse> res = new ArrayList<>();

    for (ShowEntity show : shows) {
      res.add(ShowMapper.toGetShowByMovieNameResponse(show));
    }
    return res;
  }

  @Override
  public GetShowDetailsByIdResponse getShowById(long id) {

    Optional<ShowEntity> showResponse = showRepository.findById(id);
    if (!showResponse.isPresent()) throw new RuntimeException("Show not Found for Id : " + id);

    ShowEntity show = showResponse.get();
    List<ShowSeatsEntity> showSeats = show.getShowSeats();
    List<ShowSeatsDTO> showSeatsDTOS = new ArrayList<>();

    for (ShowSeatsEntity showSeat : showSeats) {
      ShowSeatsDTO showSeatDTO = ShowSeatsDTO.builder()
        .id(showSeat.getId())
        .seatNo(showSeat.getSeatNo())
        .seatType(showSeat.getSeatType().toString())
        .price(showSeat.getPrice())
        .isBooked(showSeat.isBooked())
        .build();

      showSeatsDTOS.add(showSeatDTO);
    }

    return GetShowDetailsByIdResponse.builder()
      .id(show.getId())
      .rateMultiplier(show.getRateMultiplier())
      .startTime(show.getStartTime())
      .endTime(show.getEndTime())
      .showSeats(showSeatsDTOS)
      .build();
  }

  @Override
  public GetTheaterWiseShowsByMovieIdResponse getTheaterWiseShowByMovieId(long movieId) {
    Optional<MovieEntity> movieResponse = movieRepository.findById(movieId);

    if (!movieResponse.isPresent()) throw new RuntimeException("Movie Not Found !!! ");

    List<ShowEntity> shows = showRepository.findByMovie(movieResponse.get());

    Map<TheaterEntity, List<ShowEntity>> theaterShowsMap = shows.stream()
      .collect(Collectors.groupingBy(ShowEntity::getTheater));

    List<GetTheaterWiseShowsByMovieIdResponse.TheaterShow> theaterShowList = new ArrayList<>();

    // convert entities to DTO
    for (Map.Entry<TheaterEntity, List<ShowEntity>> entry : theaterShowsMap.entrySet()) {
      TheaterDTO theaterDTO = TheaterMapper.toDTO(entry.getKey());
      List<ShowDTO> showDTOList = entry.getValue().stream().map(item -> ShowMapper.toDTO(item)).collect(Collectors.toList());

      theaterShowList.add(GetTheaterWiseShowsByMovieIdResponse.TheaterShow.builder()
        .theater(theaterDTO)
        .shows(showDTOList).build());
    }

    return GetTheaterWiseShowsByMovieIdResponse.builder().theaterShows(theaterShowList).build();
  }

  public List<ShowSeatsEntity> getShowSeats(TheaterHallEntity theaterHall, double rateMultiplier) {
    List<TheaterHallSeatsEntity> hallSeats = theaterHall.getTheaterHallSeats();
    List<ShowSeatsEntity> showSeats = new ArrayList<>();

    for (TheaterHallSeatsEntity hallSeat : hallSeats) {
      ShowSeatsEntity showSeat = getShowSeat(hallSeat, rateMultiplier);
      showSeats.add(showSeat);
    }
    return showSeats;
  }

  public ShowSeatsEntity getShowSeat(TheaterHallSeatsEntity hallSeat, double rateMultiplier) {
    return ShowSeatsEntity.builder()
      .seatNo(hallSeat.getSeatNumber())
      .seatType(hallSeat.getSeatType())
      .isBooked(false)
      .price(hallSeat.getPrice() * rateMultiplier)
      .build();
  }
}
