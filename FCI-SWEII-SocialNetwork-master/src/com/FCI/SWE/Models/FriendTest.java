package com.FCI.SWE.Models;
import java.util.ArrayList;

import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.FCI.SWE.Controller.ConnectionC;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;
public class FriendTest {
Friend friend = new Friend();
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
  @Test
  public void getFriends() {
	 String str =  friend.getFriends("abdussalam");
	  AssertJUnit.assertEquals(str,"kamal hamed");  
  }
  @Test
  public void saveFriends() {
    boolean save = friend.saveFriends("islam", "1");
    AssertJUnit.assertEquals(save,true); 
  }
  @Test
  public void saveUser2() {  
  }
  @Test
  public void searchForReq() {
	  
     ArrayList<UserEntity>list = friend.searchForReq("abdussalam");
     AssertJUnit.assertEquals(list,"{hamed , islam , kamal}"); 
  }
}
