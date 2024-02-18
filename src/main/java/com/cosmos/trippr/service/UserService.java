package com.cosmos.trippr.service;

import com.cosmos.trippr.controller.utils.AddUserRequest;
import com.cosmos.trippr.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
  public UserEntity addUser(AddUserRequest addUserRequest);
}
