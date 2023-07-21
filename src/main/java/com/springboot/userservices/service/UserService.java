package com.springboot.userservices.service;

import lombok.Data;
import com.springboot.userservices.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 */
@Service
public @Data class UserService
{
  private List<UserModel> users = new ArrayList<>();
  private UserModel user;

  /**
   * parameterised constructor to inject dependency of UserModel class
   *
   * @param user object of UserModel class
   */
  @Autowired
  public UserService(UserModel user)
  {
    this.user = user;
  }

  /**
   * Adds a new user to the list of users
   *
   * @param newUser object of UserModel class
   */
  public void addUser(UserModel newUser)
  {
    if (newUser.getId() < 0)
    {
      throw new IllegalArgumentException();
    }
    this.user = newUser;
    users.add(user);
  }

  /**
   * Returns a user based on id passed in GET method
   *
   * @param id id to find user against
   * @return returns user if present against id, otherwise returns null
   */
  public UserModel getUser(int id)
  {
    for (UserModel user : users)
    {
      if (user.getId() == id)
      {
        return user;
      }
    }
    return null;
  }

  /**
   * gets all users in list of UserModel class
   *
   * @return complete list of users
   */
  public List<UserModel> getAllUsers()
  {
    return users;
  }

}
