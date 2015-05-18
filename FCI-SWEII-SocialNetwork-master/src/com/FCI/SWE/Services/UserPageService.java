package com.FCI.SWE.Services;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.json.simple.JSONObject;

import com.FCI.SWE.Models.KeepUserName;
import com.FCI.SWE.Models.UserEntity;
@Path("/")
@Produces("text/html")
public class UserPageService {

	@POST
	@Path("/UserspageService")
	public String userService(@FormParam("uname") String uname,
			@FormParam("pass") String pass) {
		JSONObject object = new JSONObject();
		KeepUserName userName = new KeepUserName();
		UserEntity user = UserEntity.getUserOnly(uname);
		if (user == null) {
			object.put("Status", "Failed");
		} else {
			object.put("Status", "OK");
			object.put("name", user.getName());
			object.put("email", user.getEmail());
			object.put("password", user.getPass());
		}

		return object.toString();
	}
	
}
