package com.springboot.userservices.service;

import com.springboot.userservices.model.UserModel;
import org.junit.Test;

import static org.junit.Assert.*;

import org.mockito.Mockito;

import java.util.Objects;

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

  @Test
  public void testRemoveUser_removesUser()
  {
    fixture.givenUserIsInitialized(1, "Eric", "Cartman", "CEO");
    fixture.whenUserIsAdded();  //to reduce redundancy in Fixture class
    fixture.whenUserIsDeleted();
    fixture.thenCountOfUserIs(0);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testRemoveUser_userDoesNotExist_doesNotRemoveUser_throwsException()
  {
    fixture.givenUserIsInitialized(1, "Eric", "Cartman", "CEO"); // User count=1
    fixture.whenUserIsAdded();
    fixture.givenUserIsReInitializedAfterAddition(10, "Scully", "Cartman", "CEO"); // assigning same object to a new reference
    fixture.whenUserIsDeleted();  // will look for Eric in usersArray, won't find it so won't delete, count remains 1
    fixture.thenCountOfUserIs(1);
    fixture.thenUserServiceThrewIndexOutOfBoundException();
  }

  @Test
  public void testUpdateUser_updatesUserSuccessfully()
  {
    fixture.givenUserIsInitialized(1, "Eric", "Cartman", "CEO");
    fixture.whenUserIsAdded();
    fixture.whenUserIsUpdated("Scully", "Cartman", "Manager");
    fixture.thenCheckIfUserIsUpdatedSuccessfully();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUpdateUser_enteredInvalidAttributes_doesNotUpdateUser_throwsException()
  {
    fixture.givenUserIsInitialized(1, "Eric", "Cartman", "CEO");
    fixture.whenUserIsAdded();
    fixture.whenUserIsUpdated("", "", "");
    fixture.thenCheckIfUserIsUpdatedSuccessfully();
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

    public void thenUserServiceThrewIndexOutOfBoundException()
    {
      assertEquals(IndexOutOfBoundsException.class, exception.getClass());
      throw new IndexOutOfBoundsException();
    }

    public void whenUserIsDeleted()
    {
      try
      {
        this.userService.removeUser(this.mockedUser.getId());
      }
      catch (IndexOutOfBoundsException e)
      {
        this.exception = e;
      }

    }

    public void givenUserIsReInitializedAfterAddition(int id, String firstName, String lastName, String employeeType)
    {
      mockedUser = new UserModel();
      this.mockedUser.setId(id);
      this.mockedUser.setFirstName(firstName);
      this.mockedUser.setLastName(lastName);
      this.mockedUser.setEmployeeType(employeeType);
    }

    public void whenUserIsUpdated(String newFirstName, String newLastName, String newEmployeeType)
    {
      String firstName = newFirstName;
      String lastName = newLastName;
      String employeeType = newEmployeeType;
      try
      {
        this.userService.updateUser(mockedUser.getId(), firstName, lastName, employeeType);
      }
      catch (IllegalArgumentException e)
      {
        this.exception = e;
      }
    }

    public void thenCheckIfUserIsUpdatedSuccessfully()
    {
      UserModel updatedUser = userService.getUsers().get(mockedUser.getId());
      boolean checkEqual = Objects.equals(mockedUser, updatedUser);
      assertTrue(checkEqual);
    }
  }

}

