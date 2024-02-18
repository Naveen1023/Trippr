package com.cosmos.trippr.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserDTO {
  private long id;
  private String name;
  private String mobile;
}
