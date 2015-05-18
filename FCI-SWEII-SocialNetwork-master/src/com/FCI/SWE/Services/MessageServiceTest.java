package com.FCI.SWE.Services;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class MessageServiceTest {
	MessageService message = new MessageService();
  @Test
  public void message() {
    String status = message.message("","");
    AssertJUnit.assertEquals(status,"OK");
  }
}
