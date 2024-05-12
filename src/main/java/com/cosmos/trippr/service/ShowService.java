package com.cosmos.trippr.service;

import com.cosmos.trippr.controller.requestResponseModels.AddShowRequest;
import com.cosmos.trippr.controller.requestResponseModels.GetShowsByMovieNameResponse;
import com.cosmos.trippr.controller.requestResponseModels.GetTheaterWiseShowsByMovieIdResponse;
import com.cosmos.trippr.entity.ShowEntity;
import com.cosmos.trippr.service.pojos.GetShowDetailsByIdResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShowService {

  public ShowEntity addShow(AddShowRequest addShowRequest) throws Exception;

  void deleteShow(long id);

  List<ShowEntity> getShowsByTheaterId(long id);

  List<GetShowsByMovieNameResponse> getByMovieName(String movieName);

  GetShowDetailsByIdResponse getShowById(long id);

  GetTheaterWiseShowsByMovieIdResponse getTheaterWiseShowByMovieId(long movieId);
}
