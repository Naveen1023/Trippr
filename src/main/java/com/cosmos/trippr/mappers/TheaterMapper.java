package com.cosmos.trippr.mappers;

import com.cosmos.trippr.dto.TheaterDTO;
import com.cosmos.trippr.entity.TheaterEntity;

import java.text.DecimalFormat;

public class TheaterMapper {

  public static TheaterEntity toEntity(TheaterDTO theaterDTO) {
    DecimalFormat df = new DecimalFormat("#.#####");
    return TheaterEntity.builder()
      .name(theaterDTO.getName())
      .numberOfHalls(theaterDTO.getNumberOfHalls())
      .address(theaterDTO.getAddress())
      .longitude(Double.parseDouble(df.format(theaterDTO.getLongitude())))
      .latitude(Double.parseDouble(df.format(theaterDTO.getLatitude())))
      .build();
  }

  public static TheaterDTO toDTO(TheaterEntity theater) {
    return TheaterDTO.builder()
      .id(theater.getId())
      .name(theater.getName())
      .numberOfHalls(theater.getNumberOfHalls())
      .address(theater.getAddress())
      .longitude(theater.getLongitude())
      .latitude(theater.getLatitude())
      .build();
  }
}
