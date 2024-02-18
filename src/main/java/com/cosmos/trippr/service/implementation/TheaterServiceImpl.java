package com.cosmos.trippr.service.implementation;

import com.cosmos.trippr.dto.TheaterDTO;
import com.cosmos.trippr.entity.TheaterEntity;
import com.cosmos.trippr.mappers.TheaterMapper;
import com.cosmos.trippr.repository.TheaterRepository;
import com.cosmos.trippr.service.TheaterService;
import com.cosmos.trippr.service.pojos.AddTheaterResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
public class TheaterServiceImpl implements TheaterService {

  @Autowired
  private TheaterRepository theaterRepository;

  @Override
  public AddTheaterResponse addTheater(TheaterDTO theaterDTO) {
    log.info("Theater Service adding new theater....." + theaterDTO.getName());

    TheaterEntity theater = TheaterMapper.toEntity(theaterDTO);

    TheaterEntity savedTheater = theaterRepository.save(theater);
    return new AddTheaterResponse(savedTheater.getId());
  }

  @Override
  public TheaterDTO getTheater(long id) throws Exception {
    Optional<TheaterEntity> theater = theaterRepository.findById(id);

    log.error("Checking for theater with id: " + id);

    if (!theater.isPresent()) {
      log.error("No theater exists for id: " + id);
      throw new Exception("Theater Not found Exception for Id : "+id);
    }
    return TheaterMapper.toDTO(theater.get());
  }

  @Override
  public void deleteTheater(long id) {
    theaterRepository.deleteById(id);
  }
}
