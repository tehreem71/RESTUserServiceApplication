package com.springboot.userservices.service;

import com.springboot.userservices.model.UserModel;
import org.junit.Test;

import static org.junit.Assert.*;

import org.mockito.Mockito;
import org.springframework.context.annotation.ComponentScan;

import static org.mockito.Mockito.when;

/**
 * Test class for UserService.java
 */
public class UserServiceTest
{
  private Fixture fixture = new Fixture();

  @Test
  public void testAddUser_addsUser()
  {
    fixture.givenUserIsInitialized(1, "Eric", "Cartman", "CEO");
    fixture.whenUserIsAdded();
    fixture.thenCountOfUserIs(1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddUser_idIsNegative_doesNotAddUser_throwsException()
  {
    fixture.givenUserIsInitialized(-1, "Eric", "Cartman", "CEO");
    fixture.whenUserIsAdded();
    fixture.thenCountOfUserIs(0);
    fixture.thenUserServiceThrewIllegalArgumentException();
  }



  private class Fixture
  {
    private UserModel mockedUser = Mockito.mock(UserModel.class);
    private UserService userService = new UserService(mockedUser);
    private Exception exception = new Exception();

    public void givenUserIsInitialized(int id, String firstName, String lastName, String employeeType)
    {
      this.mockedUser.setId(id);
      this.mockedUser.setFirstName(firstName);
      this.mockedUser.setLastName(lastName);
      this.mockedUser.setEmployeeType(employeeType);
      if (id < 0)
      {
        when(mockedUser.getId()).thenReturn(-1);   //specifying Mocked object to put value in getId as -1
      }
    }

    public void whenUserIsAdded()
    {
      try
      {
        this.userService.addUser(mockedUser);
      }
      catch (IllegalArgumentException e)
      {
        this.exception = e;
      }
    }

    public void thenCountOfUserIs(int expectedCount)
    {
      assertEquals(expectedCount, userService.getUsers().size());
    }

    public void thenUserServiceThrewIllegalArgumentException()
    {
      assertEquals(IllegalArgumentException.class, exception.getClass());    //check if exception thrown is same as given
      throw new IllegalArgumentException();
    }

  }

}

