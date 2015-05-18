package com.FCI.SWE.Services;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.json.simple.JSONObject;

import com.FCI.SWE.Models.KeepUserName;
import com.FCI.SWE.Models.Post;
@Path("/")
@Produces("text/html")
public class PostService {
	@POST
	@Path("/PostpageService")
	public String postPage(@FormParam("name") String publicPost,
			@FormParam("share") String sharePost,
			@FormParam("privacy") String privacyPost , @FormParam("hashTag") String hashTage) {
		JSONObject object = new JSONObject();
		KeepUserName userName = new KeepUserName();
		//UserEntity u = new UserEntity();
	  Post u = new Post();
		if (privacyPost != null) {
			
			u.writePost(userName.getUserName(), privacyPost, "1", "0");
			System.out.println(privacyPost + "privacyPost");
		}
		if (publicPost != null)
			u.writePost(userName.getUserName(), publicPost, "0", "1");
		if (sharePost != null)
			u.writePost(userName.getUserName(), sharePost, "0", "1");
/*
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
		}*/
		return object.toString();
	}

}
