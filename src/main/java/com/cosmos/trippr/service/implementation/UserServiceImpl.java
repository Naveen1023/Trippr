package com.cosmos.trippr.service.implementation;

import com.cosmos.trippr.controller.utils.AddUserRequest;
import com.cosmos.trippr.entity.UserEntity;
import com.cosmos.trippr.repository.UserRepository;
import com.cosmos.trippr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserEntity addUser(AddUserRequest addUserRequest) {
    UserEntity user = UserEntity.builder()
      .name(addUserRequest.getName())
      .mobile(addUserRequest.getMobile())
      .build();

    return userRepository.save(user);
  }
}
