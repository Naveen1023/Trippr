package com.cosmos.trippr.mappers;

import com.cosmos.trippr.dto.UserDTO;
import com.cosmos.trippr.entity.UserEntity;

public class UserMapper {

  public static UserEntity toEntity(UserDTO userDTO) {
    return UserEntity.builder()
      .name(userDTO.getName())
      .mobile(userDTO.getMobile())
      .build();
  }

  public static UserDTO toDTO(UserEntity user) {
    return UserDTO.builder()
      .id(user.getId())
      .name(user.getName())
      .mobile(user.getMobile())
      .build();
  }
}
