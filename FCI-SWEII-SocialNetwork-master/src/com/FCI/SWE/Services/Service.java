package com.FCI.SWE.Services;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.xml.ws.Action;

import org.json.simple.JSONObject;

import com.FCI.SWE.Models.KeepUserName;
import com.FCI.SWE.Models.UserEntity;

/**
 * This class contains REST services, also contains action function for web
 * application
 * 
 * @author Mohamed Samir
 * @version 1.0
 * @since 2014-02-12
 */
@Path("/")
@Produces("text/html")
public class Service {

	/*
	 * @GET
	 * 
	 * @Path("/index") public Response index() { return Response.ok(new
	 * Viewable("/jsp/entryPoint")).build(); }
	 */

	/**
	 * Registration Rest service, this service will be called to make
	 * registration. This function will store user data in data store
	 * 
	 * @param uname
	 *            provided user name
	 * @param email
	 *            provided user email
	 * @param pass
	 *            provided password
	 * @return Status json
	 */
	
	@POST
	@Path("/RegistrationService")
	public String registrationService(@FormParam("uname") String uname,
			@FormParam("email") String email, @FormParam("password") String pass) {

		UserEntity user = new UserEntity(uname, email, pass);
		user.saveUser();
		JSONObject object = new JSONObject();
		object.put("Status", "OK");

		return object.toString();
	}

	/**
	 * Login Rest Service, this service will be called to make login process
	 * also will check user data and returns new user from datastore
	 * 
	 * @param uname
	 *            provided user name
	 * @param pass
	 *            provided user password
	 * @return user in json format
	 */
	@POST
	@Path("/LoginService")
	public String loginService(@FormParam("uname") String uname,
			@FormParam("password") String pass) {
		KeepUserName userName = new KeepUserName(uname);
		JSONObject object = new JSONObject();
		UserEntity user = UserEntity.getUser(uname, pass);
		if (user == null) {
			object.put("Status", "Failed");
			System.out.println("user is faild");
			
		} else {
			System.out.println("user is OK");
			object.put("Status", "OK");
			object.put("name", user.getName());
			object.put("email", user.getEmail());
			object.put("password", user.getPass());
		}
		return object.toString();
	}

	@POST
	@Path("/UserspageService")
	public String userService(@FormParam("uname") String uname,
			@FormParam("password") String pass) {
		JSONObject object = new JSONObject();
		UserEntity user = UserEntity.getUserOnly(uname);
		if (user == null) {
			object.put("Status", "Failed");
		} else{
			object.put("Status", "OK");
			object.put("name", user.getName());
			object.put("email", user.getEmail());
			object.put("password", user.getPass());
		}
		return object.toString();
	}
	
	@POST
	@Path("/MessageService")
	public String message(@FormParam("name") String name , @FormParam("message") String message) {
		JSONObject object = new JSONObject();
		UserEntity user = UserEntity.getUserOnly(name);
		if (user == null  ){
			System.out.println("the user is null" );
			object.put("Status", "Failed");
		}else {
		user.saveMessage(name, message);
		object.put("Status", "OK");	
		}
		return object.toString();
	}
	
	
	@POST
	@Path("/GroupMessage")
	public String groupMessage(@FormParam("groupName") String groupName , @FormParam("memmber") String memmber,
			@FormParam("message") String message) {
		
		JSONObject object = new JSONObject();
		KeepUserName admin = new KeepUserName(); 
		UserEntity group = UserEntity.getGroupName(groupName);
		UserEntity user2 = new UserEntity();
		System.out.println("groupName : "+groupName);
		System.out.println("group : "+group);
		System.out.println("memmber : "+memmber);
		System.out.println("message  : "+message);
		
		if(groupName.equals(null) && memmber.equals(null) && message.equals(null) ){
			object.put("Status", "OK");
		}else if(group==null){
			System.out.println("tr1");
			user2.createGroupMessage(groupName, admin.getUserName(), message);//create group only
				System.out.println("tr34");
				object.put("Status", "OK");	
		}else if(  group != null && memmber == null ){
			System.out.println("tr2") ;
			user2.createGroupMessage(groupName, memmber, message);
			object.put("Status", "OK");
			System.out.println("tr4");
		}else {
			user2.createGroupMessage(groupName,memmber, message);
			object.put("Status", "OK");
			System.out.println("tr3");	
		}
		return object.toString();
	}
}