package com.FCI.SWE.Models;
import java.util.ArrayList;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import com.google.api.server.spi.auth.common.User;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class UserEntityTest {
	public final UserEntity userEntity = new UserEntity( );
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
			/*
			@DataProvider(name="getUserOnly")
			public static Object[][]getUserOnly (){
				return new Object[][]{{2 , true},{3,false} , {-3 ,false }, {5 ,false }};
			}*/
			/*
			@DataProvider(name="even")
			public static Object[][]getUserOnly (){
				return new Object[][]{{2 , true},{3,false} , {-3 ,false }, {5 ,false }};
			}*/	
@Test
  public void userEntityTest(){
	Page page = new Page();
	UserEntity u = UserEntity.getUserOnly("abdussalam");
	AssertJUnit.assertEquals(u.name,"abdussalam" );
	GroupMessage gMessage = new GroupMessage();
	  u = gMessage.getGroupName("ISLAM");
	  Friend friend = new Friend();
	AssertJUnit.assertEquals(u.name,"ISLAM" );
	
	  String friends = friend.getFriends("abdussalam");
		AssertJUnit.assertEquals(friends,"kamal hamed islam" );
		
		  u = page.getpage("FCI");
			AssertJUnit.assertEquals(u.name,"FCI" );
			
			 ArrayList<UserEntity> messages = gMessage.getGroupMessages("abdussalam", "hamed");
				AssertJUnit.assertEquals(messages,"{hello abdussalam , hamed}" );
		
	
	
  }
}

