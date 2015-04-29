package com.FCI.SWE.Services;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.xml.ws.Action;

import org.json.simple.JSONObject;

import com.FCI.SWE.Models.KeepUserName;
import com.FCI.SWE.Models.UserEntity;

/**
 * @author Kamal Alhusam
 * @author Islam imam
 * @author Hamed alghazaly
 * @author Abdussalam Al-shouiby
 * @version 1.0
 * @since 2015-03-12
 */
@Path("/")
@Produces("text/html")
public class Service {
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
		JSONObject object = new JSONObject();
		object.put("Status", "OK");
		object.put("name", user.getName());
		object.put("email", user.getEmail());
		object.put("password", user.getPass());

		return object.toString();
	}

	@POST
	@Path("/LoginService")
	public String loginService(@FormParam("uname") String uname,
			@FormParam("password") String pass) {
		KeepUserName userName = new KeepUserName(uname);
		JSONObject object = new JSONObject();
		UserEntity user = UserEntity.getUser(uname, pass);
		if (user == null) {
			object.put("Status", "Failed");

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

	@POST
	@Path("/PostpageService")
	public String postPage(@FormParam("posts") String post) {
		JSONObject object = new JSONObject();
		KeepUserName userName = new KeepUserName();
		UserEntity user = UserEntity.getUserOnly(post);
		ArrayList<String> str = new ArrayList<>();
		if (post != null) {
			user.writePost(userName.getUserName(), post);
			String hashTag = "";
			str.add(post);
			int x = str.indexOf('#');
			if (x >= 0) {
				while (true) {
					if (str.get(x) == " ")
						break;
					hashTag += str.get(x);
					x++;
				}
				System.out.println("hashTag : " + hashTag);
				user.writeHashTag(userName.getUserName(), hashTag, post);
			}
		}

		return object.toString();
	}

	@POST
	@Path("/PagespageService")
	public String pagesPage(@FormParam("pageName") String pageName,
			@FormParam("pName") String searchPage) {
		JSONObject object = new JSONObject();
		KeepUserName userName = new KeepUserName();
		// UserEntity user = UserEntity.getpage(searchPage);
		System.out.println("pages apge " + pageName);
		UserEntity u = new UserEntity();

		// // if (user == null && pageName == null ) return null ;
		// if(pageName == null)
		// u.savePage( searchPage, userName.getUserName());

		u.savePage(pageName, searchPage); // create page

		return object.toString();
	}

	@POST
	@Path("/MessageService")
	public String message(@FormParam("name") String name,
			@FormParam("message") String message) {
		JSONObject object = new JSONObject();
		UserEntity user = UserEntity.getUserOnly(name);
		if (user == null) {
			System.out.println("the user is null");
			object.put("Status", "Failed");
		} else {
			user.saveMessage(name, message);
			object.put("Status", "OK");
		}
		return object.toString();
	}

	@POST
	@Path("/GroupMessage")
	public String groupMessage(@FormParam("groupName") String groupName,
			@FormParam("memmber") String memmber,
			@FormParam("message") String message) {
		JSONObject object = new JSONObject();
		KeepUserName admin = new KeepUserName();
		UserEntity group = UserEntity.getGroupName(groupName);
		UserEntity user2 = new UserEntity();
		if (groupName.equals(null) && memmber.equals(null)
				&& message.equals(null)) {
			object.put("Status", "OK");
		} else if (group == null) {
			user2.createGroupMessage(groupName, admin.getUserName(), message);
			object.put("Status", "OK");
		} else if (group != null && memmber == null) {
			user2.createGroupMessage(groupName, memmber, message);
			object.put("Status", "OK");

		} else {
			user2.createGroupMessage(groupName, memmber, message);
			object.put("Status", "OK");
		}
		return object.toString();
	}
}