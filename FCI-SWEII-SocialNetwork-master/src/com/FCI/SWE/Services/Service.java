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

		System.out.println("HHH");
		JSONObject object = new JSONObject();
		object.put("Status", "OK");
		object.put("name", user.getName());
		object.put("email", user.getEmail());
		object.put("password", user.getPass());
		// if (uname==null) {
		System.out.println("heyeheyehey: ");
		UserEntity user2 = new UserEntity();
		KeepUserName userName = new KeepUserName();
		user2.saveUser2(userName.getUserName(), "1", uname);
		// return object.toString();
		// }
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
	public String postPage(@FormParam("name") String publicPost,
			@FormParam("share") String sharePost,
			@FormParam("privacy") String privacyPost , @FormParam("hashTag") String hashTage) {
		JSONObject object = new JSONObject();
		KeepUserName userName = new KeepUserName();
		UserEntity u = new UserEntity();
	 
		System.out.println(privacyPost + " privacyPost" + "sharePost: "
				+ sharePost + " publicPost :" + publicPost);
		if (privacyPost != null) {
			
			u.writePost(userName.getUserName(), privacyPost, "1", "0");
			System.out.println(privacyPost + "privacyPost");
		}
		if (publicPost != null)
			u.writePost(userName.getUserName(), publicPost, "0", "1");
		if (sharePost != null)
			u.writePost(userName.getUserName(), sharePost, "0", "1");

 	ArrayList<String> str = new ArrayList<>();
		UserEntity user = new UserEntity();
		String hashTag = "";
		str.add(publicPost);

		int x = str.get(0).indexOf('#');
		int y = str.get(0).indexOf(" ", x);
		hashTag = str.get(0).substring(x, y);
		System.out.println("X is :" + x);
		if (x >= 0) {
			System.out.println("hashTag : " + hashTag);
			user.writeHashTag(userName.getUserName(), hashTag, publicPost);
		}
		return object.toString();
	}

	/*
	 * @POST
	 * 
	 * @Path("/PostpageService") public String postPage(@FormParam("name")
	 * String post) { JSONObject object = new JSONObject(); KeepUserName
	 * userName = new KeepUserName(); UserEntity user =
	 * UserEntity.getUserOnly(post); ArrayList<String> str = new ArrayList<>();
	 * System.out.println("post :"+post); user.writePost(userName.getUserName()
	 * , post); /* if (post != null) { user.writePost(userName.getUserName(),
	 * post); String hashTag = ""; str.add(post); int x = str.indexOf('#'); if
	 * (x >= 0) { while (true) { if (str.get(x) == " ") break; hashTag +=
	 * str.get(x); x++; } System.out.println("hashTag : " + hashTag);
	 * user.writeHashTag(userName.getUserName(), hashTag, post); } }
	 * 
	 * return object.toString(); }
	 */
	@POST
	@Path("/PagespageService")
	public String pagesPage(@FormParam("pageName") String pageName,
			@FormParam("pName") String searchPage) {
		JSONObject object = new JSONObject();
		KeepUserName userName = new KeepUserName();
		UserEntity u = new UserEntity();

		// // if (user == null && pageName == null ) return null ;
		 if(searchPage != null)u.savePage( searchPage, userName.getUserName());
		 if(pageName!=null)u.savePage(pageName,  userName.getUserName());
		
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