package com.lopez.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

  private String id;
  private String userName;
  private String identity;
  private String dateOfBirth;
  private String gender;
  private String occupation;
  private String riskLevel;

}
