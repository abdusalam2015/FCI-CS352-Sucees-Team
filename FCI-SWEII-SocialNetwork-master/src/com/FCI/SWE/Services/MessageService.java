package com.FCI.SWE.Services;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.json.simple.JSONObject;

import com.FCI.SWE.Models.Message;
import com.FCI.SWE.Models.UserEntity;
@Path("/")
@Produces("text/html")
public class MessageService {
	@POST
	@Path("/MessageService")
	public String message(@FormParam("name") String name,
			@FormParam("message") String message) {
		JSONObject object = new JSONObject();
		UserEntity username = new UserEntity();
	Message messages = new Message();
	username = UserEntity.getUserOnly(name);
		if (username == null) {
			System.out.println("the user is null");
			object.put("Status", "Failed");
		} else {
			messages.saveMessage(name, message);
			object.put("Status", "OK");
		}
		return object.toString();
	}


}
