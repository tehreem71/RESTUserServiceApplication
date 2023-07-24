package com.springboot.userservices.service;

import lombok.Data;
import com.springboot.userservices.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

  /**
   * Removes a user against given id in DELETE controller
   *
   * @param id id to remove user against
   */
  public void removeUser(int id)
  {
    if (!users.contains(getUser(id)))
    {
      throw new IndexOutOfBoundsException();
    }
    users.removeIf(user -> user.getId() == id);
  }

  /**
   * Updates a user attributes from list of users, matched by given id
   * @param id  id of user to be updated
   * @param firstName updated first name of user
   * @param lastName  updated last name of user
   * @param employeeType  updated employee type of user
   * @return  returns updated user object of UserModel class
   */

  public UserModel updateUser(int id, String firstName,String lastName, String employeeType)
  {

      if (firstName == "" || lastName == "" || employeeType == "")
      {
        throw new IllegalArgumentException();
      }
      for (UserModel user : users)
      {
        if (user.getId() == id)
        {
          user.setId(id);
          user.setFirstName(firstName);
          user.setLastName(lastName);
          user.setEmployeeType(employeeType);
          return user;
        }
      }

    return null;
  }
}
