package com.FCI.SWE.Services;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.json.simple.JSONObject;

import com.FCI.SWE.Models.GroupMessage;
import com.FCI.SWE.Models.KeepUserName;
import com.FCI.SWE.Models.UserEntity;
@Path("/")
@Produces("text/html")
public class GroupMessageService {
	@POST
	@Path("/GroupMessage")
	public String groupMessage(@FormParam("groupName") String groupName,
			@FormParam("memmber") String memmber,
			@FormParam("message") String message) {
		JSONObject object = new JSONObject();
		KeepUserName admin = new KeepUserName();
		GroupMessage group = new GroupMessage();
		group.getGroupName(groupName);
		UserEntity user2 = new UserEntity();
		if (groupName.equals(null) && memmber.equals(null)
				&& message.equals(null)) {
			 
		} else if (group == null) {
			group.createGroupMessage(groupName, admin.getUserName(), message);
		} else if (group != null && memmber == null) {
			group.createGroupMessage(groupName, admin.getUserName(), message);
			 

		} else if(memmber != null){
			group.createGroupMessage(groupName, memmber, message);
			
		}else group.createGroupMessage(groupName, admin.getUserName(), message);
		object.put("Status", "OK");
		return object.toString();
	}
}
