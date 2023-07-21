package com.springboot.userservices.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component  //declaring it as a bean
public @Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor class UserModel
{
  @NonNull
  private int id;
  @NonNull
  private String firstName;
  private String lastName;
  private String employeeType;
}

