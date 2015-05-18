package com.FCI.SWE.Controller;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.sun.jndi.ldap.Connection;

public class UserControllerTessting {
	
	UserController user = new UserController();
	ConnectionTest object = new ConnectionTest();
  @Test
  public void   UserController() {
	  
	  String urlParameters = "uname=" + "kamal" + "&password=" + "1234";
	  String serviceUrl = "http://localhost:8888/rest/LoginService";
	  String result =  object.connect(urlParameters , serviceUrl) ;
	  user.login() ;
	  AssertJUnit.assertEquals(result,"kamal 1234");
  }
}
