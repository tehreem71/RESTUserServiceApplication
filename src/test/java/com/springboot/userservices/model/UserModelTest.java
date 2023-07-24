package com.springboot.userservices.model;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class UserModelTest
{
  private UserModel user = new UserModel();

  @Test
  public void test_getterSetterForId()
  {
    user.setId(1);
    int expectedId = 1;
    assertEquals(expectedId, user.getId());
  }

  @Test
  public void test_getterSetterForFirstName()
  {
    user.setFirstName("Ali");
    String expectedFirstName = "Ali";
    assertEquals(expectedFirstName, user.getFirstName());
  }

  @Test
  public void test_getterSetterForLastName()
  {
    user.setLastName("Asghar");
    String expectedLastName = "Asghar";
    assertEquals(expectedLastName, user.getLastName());
  }

  @Test
  public void test_getterSetterForEmployeeType()
  {
    user.setEmployeeType("Manager");
    String expectedEmployeeType = "Manager";
    assertEquals(expectedEmployeeType, user.getEmployeeType());
  }

}
