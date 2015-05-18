package com.FCI.SWE.Services;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.json.simple.JSONObject;

import com.FCI.SWE.Models.Friend;
import com.FCI.SWE.Models.KeepUserName;
import com.FCI.SWE.Models.UserEntity;
@Path("/")
@Produces("text/html")
public class RegestrationService {

	@POST
	@Path("/RegistrationService")
	public String registrationService(@FormParam("uname") String uname,
			@FormParam("email") String email,
			@FormParam("password") String password,
			@FormParam("birthdate") String birthdate,
			@FormParam("live") String live,
			@FormParam("nationality") String nationality,
			@FormParam("student") String student) {
		UserEntity user = new UserEntity(uname, email, password, birthdate,
				live, nationality, student);
		user.saveUser();

		System.out.println("HHH");
		JSONObject object = new JSONObject();
		object.put("Status", "OK");
		object.put("name", user.getName());
		object.put("email", user.getEmail());
		object.put("password", user.getPass());
		// if (uname==null) {
		System.out.println("heyeheyehey: ");
		UserEntity user2 = new UserEntity();
		Friend obj = new Friend ();
		KeepUserName userName = new KeepUserName();
		obj.saveUser2(userName.getUserName(), "1", uname);
		// return object.toString();
		// }
		return object.toString();
	}


}
