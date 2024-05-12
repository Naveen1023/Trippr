package com.cosmos.trippr.controller.requestResponseModels;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;


@Data
public class ResponseBase {

  public static ResponseEntity<Object> generateResponse(HttpStatus status, Object data, String message) {
    Map<String, Object> map = new HashMap<>();
    map.put("status", status);
    map.put("data", data);
    map.put("message", message);
    return new ResponseEntity<Object>(map, status);
  }
}
