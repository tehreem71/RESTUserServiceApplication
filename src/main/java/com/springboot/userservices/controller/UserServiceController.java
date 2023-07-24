package com.springboot.userservices.controller;

import com.springboot.userservices.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.userservices.service.UserService;

import java.util.List;

@RestController
//@ComponentScan("com.springboot.userservices")
//recognizes given classes in package as beans w/o declaring @component. can recognize @component, @service, @repository,etc
//app will work fine w/o it if all components are in same parent package
public class UserServiceController
{
  private UserService userService;
  private UserModel user;

  /**
   * constructor to inject dependency from service and model using @Autowired
   *
   * @param userService service class object for DI
   * @param user model class object for DI
   */
  @Autowired
  public UserServiceController(UserService userService, UserModel user)
  {
    this.userService = userService;
    this.user = user;
  }

  /**
   * POST method that adds a new user to list of users
   *
   * @param newClient object of user model to be added
   */
  @PostMapping("/addUser")
  public void addUser(@RequestBody UserModel newClient)
  {
    this.user = newClient;
    userService.addUser(user);
  }

  /**
   * GET method to return complete list of users
   *
   * @return list of all users
   */
  @GetMapping("/getAllUsers")
  public List getAllUsers()
  {
    return userService.getAllUsers();
  }

  /**
   * GET method to return a specific user based on id
   *
   * @param id integer path variable to get specific user
   * @return user based on id
   */
  @GetMapping("/getUser/{id}")
  public UserModel getUser(@PathVariable int id)
  {
    return userService.getUser(id);
  }

  /**
   * DELETE method to remove a specific user from users list based on id
   *
   * @param id integer path variable to remove specific user
   */
  @DeleteMapping("/deleteUser/{id}")
  public void removeUser(@PathVariable int id)
  {
    userService.removeUser(id);
  }

  /**
   * PUT method to update attributes of a single user
   *
   * @param id id of user to be updated passed as a path variable
   * @param updatedUser JSON object of UserModel passed in request body
   * @return returns updated user object
   */
  @PutMapping("/updateUser/{id}")
  public UserModel updateUser(@PathVariable int id, @RequestBody UserModel updatedUser)
  {
    this.user = updatedUser;
    userService.updateUser(id, user.getFirstName(), user.getLastName(), user.getEmployeeType());
    return user;
  }


}
