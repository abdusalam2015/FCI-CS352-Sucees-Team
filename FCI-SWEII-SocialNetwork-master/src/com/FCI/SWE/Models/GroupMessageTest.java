package com.FCI.SWE.Models;

import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class GroupMessageTest {
	private final LocalServiceTestHelper helper1 =
			new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
	
			@BeforeClass
			public void setUp() {
			helper1.setUp();
			}
			@AfterClass
			public void tearDown() {
			helper1.tearDown();
			}
	GroupMessage gMessage = new GroupMessage();
  @Test
  public void createGroupMessage() {
	 boolean check =  gMessage.createGroupMessage("FCI", "", "");
	  AssertJUnit.assertEquals(check,true);  
  }

  @Test
  public void getGroupMessages() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void getGroupName() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void getNotificationGMSG() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void getUser() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void getconversations() {
    throw new RuntimeException("Test not implemented");
  }
}
