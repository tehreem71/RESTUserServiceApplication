package com.springboot.userservices.service;

import lombok.Data;
import com.springboot.userservices.model.UserModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
@Data
public class UserService
{
  private List<UserModel> users = new ArrayList<>();
  private static final Logger logger= LogManager.getLogger("userService");
  /**
   * Adds a new user to the list of users
   *
   * @param newUser object of UserModel class
   */
  public void addUser(UserModel newUser)
  {
    if (newUser.getId() <= 0)
    {
      throw new IllegalArgumentException("Invalid user ID: " + newUser.getId());
    }
    users.add(newUser);
    logger.info("successfully added new user");
  }

  /**
   * Returns a user based on id passed in GET method
   *
   * @param id id to find user against
   * @return returns user if present against id, otherwise returns null
   */
  public UserModel getUser(Integer id)
  {
    for (UserModel user : users)
    {
      if (user.getId() == id)
      {
        logger.info("User found - ID: {}", user.getId());
        return user;
      }
    }
    logger.error("User not found - ID: {}", id);
    return null;
  }

  /**
   * gets all users in list of UserModel class
   *
   * @return complete list of users
   */
  public List<UserModel> getAllUsers()
  {
    logger.info("Retrieving all users - Total users: {}", users.size());
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
      throw new IndexOutOfBoundsException("User with ID " + id + " does not exist!");
    }
    users.removeIf(user -> user.getId() == id);
    logger.info("User removed successfully - ID: {}", id);
  }

  /**
   * Updates a user attributes from list of users, matched by given id
   *
   * @param id           id of user to be updated
   * @param firstName    updated first name of user
   * @param lastName     updated last name of user
   * @param employeeType updated employee type of user
   * @return returns updated user object of UserModel class
   */

  public UserModel updateUser(Integer id, String firstName, String lastName, String employeeType)
  {
    if (id == null)
    {
      throw new IndexOutOfBoundsException("User ID can not be empty!");
    }
    for (UserModel user : users)
    {
      if (user.getId() == id)
      {
        user.setId(id);
        user.setFirstName(firstName.isEmpty() ? user.getFirstName() : firstName);
        user.setLastName(lastName.isEmpty() ? user.getLastName() : lastName);
        user.setEmployeeType(employeeType.isEmpty() ? user.getEmployeeType() : employeeType);
        logger.info("User updated successfully - ID: {}", user.getId());
        return user;
      }
    }
    logger.info("User not found for update - ID: {}", id);
    return null;
  }
}
