package com.cosmos.trippr.service;

import com.cosmos.trippr.dto.TheaterDTO;
import com.cosmos.trippr.service.pojos.AddTheaterResponse;
import org.springframework.stereotype.Service;

@Service
public interface TheaterService {

  AddTheaterResponse addTheater(TheaterDTO theaterDTO);

  TheaterDTO getTheater(long id) throws Exception;

  void deleteTheater(long id);
}
