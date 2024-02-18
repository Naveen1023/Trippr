package com.cosmos.trippr.mappers;

import com.cosmos.trippr.dto.TheaterHallDTO;
import com.cosmos.trippr.entity.TheaterHallEntity;

public class TheaterHallMapper {

  public static TheaterHallEntity toEntity(TheaterHallDTO theaterHallDTO) {
    return TheaterHallEntity.builder()
      .name(theaterHallDTO.getName())
      .numberOfSeats(theaterHallDTO.getNumberOfSeats())
      .build();
  }

  public static TheaterHallDTO toDTO(TheaterHallEntity theaterHall) {
    return TheaterHallDTO.builder()
      .id(theaterHall.getId())
      .name(theaterHall.getName())
      .numberOfSeats(theaterHall.getNumberOfSeats())
      .build();
  }
}
