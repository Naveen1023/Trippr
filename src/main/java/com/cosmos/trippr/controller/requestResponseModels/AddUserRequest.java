package com.cosmos.trippr.controller.requestResponseModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddUserRequest {
  private String name;
  private String mobile;
}
