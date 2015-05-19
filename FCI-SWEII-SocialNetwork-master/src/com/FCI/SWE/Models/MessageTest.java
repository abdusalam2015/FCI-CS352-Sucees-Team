package com.FCI.SWE.Models;

import java.util.ArrayList;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class MessageTest {
public Message message = new Message();
  @Test
  public void getMessages() {
	 ArrayList<UserEntity> list = message.getMessages("abdussalam");
	 AssertJUnit.assertEquals(list,"{islam,abdussalam,hii islam}") ;  
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void getSenderMessages() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void getSpecificMessages() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void saveMessage() {
    throw new RuntimeException("Test not implemented");
  }
}
