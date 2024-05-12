package com.cosmos.trippr.controller;

import com.cosmos.trippr.controller.requestResponseModels.AddUserRequest;
import com.cosmos.trippr.controller.requestResponseModels.ResponseBase;
import com.cosmos.trippr.entity.UserEntity;
import com.cosmos.trippr.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("user")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("add")
  public ResponseEntity<Object> addUser(@RequestBody AddUserRequest addUserRequest) {
    try {
      log.info("Request for adding a new User is received :  " + addUserRequest.getName());
      UserEntity userResponse = userService.addUser(addUserRequest);
      return ResponseBase.generateResponse(HttpStatus.OK, userResponse, "Successfully Added");
    } catch (Exception e) {
      return ResponseBase.generateResponse(HttpStatus.UNPROCESSABLE_ENTITY, null, e.getMessage());
    }
  }
}
