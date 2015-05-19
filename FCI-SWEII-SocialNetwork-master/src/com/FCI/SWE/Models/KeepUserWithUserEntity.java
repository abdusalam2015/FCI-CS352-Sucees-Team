package com.FCI.SWE.Models;

 
import org.testng.Assert;
import org.testng.annotations.Test;

 

public class KeepUserWithUserEntity {
  
	UserEntity c = new UserEntity();
	  @Test
	  public void categoryTest(  ) {
		 c  = new  UserEntity("abdussalam","abdussalam93@hotmail.com","1234");
		  Assert.assertEquals( c.getName() , "islam");
		 
	  }
	KeepUserName c2 = new KeepUserName();
	  @Test  ( dependsOnMethods = "UserEntityTest")
	  public void contactTest(){
		  c2.setUserName("abdussalam"); 
		  Assert.assertEquals( c2.getUserName() , "abdussalam");
	  }
	  
	   
	}
