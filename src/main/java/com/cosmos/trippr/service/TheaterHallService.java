package com.cosmos.trippr.service;


import com.cosmos.trippr.controller.utils.AddTheaterHallRequest;
import com.cosmos.trippr.service.pojos.AddTheaterHallResponse;
import org.springframework.stereotype.Service;

@Service
public interface TheaterHallService {

  AddTheaterHallResponse addTheaterHall(AddTheaterHallRequest addTheaterHallRequest);

  void deleteTheater(long id);
}
