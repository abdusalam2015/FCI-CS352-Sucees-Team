package com.FCI.SWE.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.FCI.SWE.Models.KeepUserName;
import com.FCI.SWE.Models.UserEntity;
import com.FCI.SWE.Services.Service;
import com.google.api.server.spi.BackendService.Properties;

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
public class UserController {

	@GET
	@Path("/signup")
	public Response signUp() {
		return Response.ok(new Viewable("/jsp/register")).build();
	}

	@GET
	@Path("/pagesPage")
	public Response pagesPage() {
		return Response.ok(new Viewable("/jsp/pagesPage")).build();
	}

	@GET
	@Path("/postPage")
	public Response postPage() {
		return Response.ok(new Viewable("/jsp/postPage")).build();
	}

	@GET
	@Path("/")
	public Response index() {
		return Response.ok(new Viewable("/jsp/entryPoint")).build();
	}

	@GET
	@Path("/login")
	public Response login() {
		return Response.ok(new Viewable("/jsp/login")).build();
	}

	@GET
	@Path("/userspage")
	public Response userspage() {
		return Response.ok(new Viewable("/jsp/userspage")).build();
	}

	@GET
	@Path("/message")
	public Response message() {
		return Response.ok(new Viewable("/jsp/message")).build();
	}

	@GET
	@Path("/groupMessage")
	public Response groupMessage() {
		return Response.ok(new Viewable("/jsp/groupMessage")).build();
	}

	@POST
	@Path("/response")
	@Produces("text/html")
	public String response(@FormParam("uname") String uname,
			@FormParam("email") String email,
			@FormParam("password") String password,
			@FormParam("birthdate") String birthdate,
			@FormParam("live") String live,
			@FormParam("nationality") String nationality,
			@FormParam("student") String student) {
		String serviceUrl = "http://localhost:8888/rest/RegistrationService";

		try {
			URL url = new URL(serviceUrl);

			String urlParameters = "uname=" + uname + "&email=" + email
					+ "&password=" + password + "&birthdate=" + birthdate
					+ "&live=" + live + "&nationality=" + nationality
					+ "&student=" + student;
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("POST");
			connection.setConnectTimeout(60000);
			connection.setReadTimeout(60000);

			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded;charset=UTF-8");
			OutputStreamWriter writer = new OutputStreamWriter(
					connection.getOutputStream());
			writer.write(urlParameters);
			writer.flush();
			String line, retJson = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));

			while ((line = reader.readLine()) != null) {
				retJson += line;
			}
			writer.close();
			reader.close();

			JSONParser parser = new JSONParser();
			Object obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("OK"))
				return "Registered Successfully";

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "Failed";
	}

	@POST
	@Path("/home")
	@Produces("text/html")
	public Response home(@FormParam("uname") String uname,
			@FormParam("password") String pass) {

		String serviceUrl = "http://localhost:8888/rest/LoginService";
		try {
			URL url = new URL(serviceUrl);
			String urlParameters = "uname=" + uname + "&password=" + pass;
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("POST");
			connection.setConnectTimeout(60000);
			connection.setReadTimeout(60000);

			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded;charset=UTF-8");
			OutputStreamWriter writer = new OutputStreamWriter(
					connection.getOutputStream());
			writer.write(urlParameters);
			writer.flush();
			String line, retJson = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));

			while ((line = reader.readLine()) != null) {
				retJson += line;
			}
			writer.close();
			reader.close();

			JSONParser parser = new JSONParser();
			Object obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("Failed"))
				return null;
			Map<String, ArrayList<UserEntity>> map = new HashMap<String, ArrayList<UserEntity>>();
			UserEntity user = UserEntity.getUser(object.toJSONString());
			ArrayList<UserEntity> list = new ArrayList<UserEntity>();
			ArrayList<UserEntity> list2 = new ArrayList<UserEntity>();
			ArrayList<UserEntity> list3 = new ArrayList<UserEntity>();
			ArrayList<UserEntity> list4 = new ArrayList<UserEntity>();
			ArrayList<UserEntity> list5 = new ArrayList<UserEntity>();
			
			KeepUserName userName = new KeepUserName();
			list2.addAll(user.searchForReq(userName.getUserName()));
			map.put("friends", list2);
			list3.addAll(user.getSenderMessages(userName.getUserName()));
			map.put("messages", list3);
			list.addAll(user.getNotificationGMSG(userName.getUserName()));
			map.put("GMSG", list);
			list4.addAll(user.getPosts(userName.getUserName())); // if table is found
			map.put("post", list4);
			list5.addAll(user.getPrivacyPosts(userName.getUserName()));
			map.put("onlyMe", list5);
			return Response.ok(new Viewable("/jsp/home", map)).build();

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@POST
	@Path("/userspage")
	@Produces("text/html")
	public Response users(@FormParam("uname") String uname,
			@FormParam("posts") String posts) {
		String serviceUrl = "http://localhost:8888/rest/UserspageService";
		try {
			URL url = new URL(serviceUrl);
			String urlParameters = "uname=" + uname + "&posts=" + uname;
			System.out.println("postHmed: " + uname);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("POST");
			connection.setConnectTimeout(60000); 
			connection.setReadTimeout(60000);

			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded;charset=UTF-8");
			OutputStreamWriter writer = new OutputStreamWriter(
					connection.getOutputStream());
			writer.write(urlParameters);
			writer.flush();
			String line, retJson = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));

			while ((line = reader.readLine()) != null) {
				retJson += line;
			}
			writer.close();
			reader.close();
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("Failed"))
				return null;
			ArrayList<UserEntity> timeLine = new ArrayList<UserEntity>();
			Map<String, ArrayList<UserEntity>> map = new HashMap<String, ArrayList<UserEntity>>();
			UserEntity user = UserEntity.getUser(object.toJSONString());
			KeepUserName n = new KeepUserName();
			user.saveFriends(n.getUserName(), "0" );
			
			timeLine.addAll(user.getTimeLine(uname));
			map.put("timeline", timeLine);
			return Response.ok(new Viewable("/jsp/userspage", map)).build();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return null;
	}

	@POST
	@Path("/groupMessage")
	@Produces("text/html")
	public Response groupMessage(@FormParam("groupName") String groupName,
			@FormParam("memmber") String memmber,
			@FormParam("message") String message) {

		String serviceUrl = "http://localhost:8888/rest/GroupMessage";
		try {

			URL url = new URL(serviceUrl);
			String urlParameters = "groupName=" + groupName + "&memmber="
					+ memmber + "&message=" + message;
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("POST");
			connection.setConnectTimeout(60000); 
			connection.setReadTimeout(60000); 

			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded;charset=UTF-8");
			OutputStreamWriter writer = new OutputStreamWriter(
					connection.getOutputStream());
			writer.write(urlParameters);
			writer.flush();
			String line, retJson = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));

			while ((line = reader.readLine()) != null) {
				retJson += line;
			}

			writer.close();
			reader.close();
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			ArrayList<UserEntity> list = new ArrayList<UserEntity>();
			 
			KeepUserName username = new KeepUserName();
			Map<String, ArrayList<UserEntity>> map = new HashMap<String, ArrayList<UserEntity>>();
			UserEntity user = new UserEntity();
			UserEntity user2 = new UserEntity();
			user2 = user2.isMemmber(username.getUserName());
			 list.clear();
			if (user2.equals(null)) {
				list.add(new UserEntity("", "", ""));
				map.put("GMSG", list);
			} else {
				list.clear();
				list.addAll(user.getGroupMessages(username.getUserName()));
				map.put("GMSG", list);
			}
			
			return Response.ok(new Viewable("/jsp/groupMessage", map)).build();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@POST
	@Path("/message")
	@Produces("text/html")
	public Response message(@FormParam("name") String name,
			@FormParam("message") String message) {

		String serviceUrl = "http://localhost:8888/rest/MessageService";
		try {

			URL url = new URL(serviceUrl);
			String urlParameters = "name=" + name + "&message=" + message;
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("POST");
			connection.setConnectTimeout(60000); 
			connection.setReadTimeout(60000); 

			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded;charset=UTF-8");
			OutputStreamWriter writer = new OutputStreamWriter(
					connection.getOutputStream());
			writer.write(urlParameters);
			writer.flush();
			String line, retJson = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));

			while ((line = reader.readLine()) != null) {
				retJson += line;
			}
			writer.close();
			reader.close();
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;

			Map<String, ArrayList<UserEntity>> map = new HashMap<String, ArrayList<UserEntity>>();
			UserEntity user = new UserEntity();
			ArrayList<UserEntity> messageList = new ArrayList<UserEntity>();
			ArrayList<UserEntity> messageList2 = new ArrayList<UserEntity>();
			KeepUserName u = new KeepUserName();
			if (name == null) {
				messageList.addAll(user.getMessages(u.getUserName()));
				map.put("messages", messageList);
			} else {
				messageList2.addAll(user.getSpecificMessages(u.getUserName(), name));
				map.put("messages", messageList2);
			}
			
		return Response.ok(new Viewable("/jsp/message", map)).build();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return null;
	}

	@POST
	@Path("/postPage")
	@Produces("text/html")
	public Response postPage(@FormParam("name") String postName,
			@FormParam("share") String sharePost , @FormParam("privacy") String privacy ,
			@FormParam("hashTag") String hashTag) {
		String serviceUrl = "http://localhost:8888/rest/PostpageService";
		try {
			URL url = new URL(serviceUrl);
			String urlParameters = "name=" + postName + "&share=" +sharePost +"&privacy=" + privacy +"&hashTag" + hashTag;
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("POST");
			connection.setConnectTimeout(60000); // 60 Seconds
			connection.setReadTimeout(60000); // 60 Seconds

			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded;charset=UTF-8");
			OutputStreamWriter writer = new OutputStreamWriter(
					connection.getOutputStream());
			writer.write(urlParameters);
			writer.flush();
			String line, retJson = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));

			while ((line = reader.readLine()) != null) {
				retJson += line;
			}
			writer.close();
			reader.close();
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			 KeepUserName userName = new KeepUserName();
			ArrayList<UserEntity> posts = new ArrayList<UserEntity>();
			ArrayList<UserEntity> hashTagList = new ArrayList<UserEntity>();
			UserEntity caller = new UserEntity();
			posts.addAll(caller.getPosts(userName.getUserName()));
			System.out.println("hashtag :"+hashTag);
			if(hashTag != null){
				
			//hashTagList.addAll(caller.getHashTag(hashTag));
			System.out.println("hashtagloop :"+hashTag);
			}
			Map<String, ArrayList<UserEntity>> map = new HashMap<String, ArrayList<UserEntity>>();
			map.put("post", posts);
			
			map.put("hashTag", hashTagList);
			Response.ok(new Viewable("/jsp/postPage", map)).build();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@POST
	@Path("/pagesPage")
	@Produces("text/html")
	public Response pagePage(@FormParam("pageName") String pageName,
			@FormParam("pName") String like) {

		String serviceUrl = "http://localhost:8888/rest/PagespageService";
		try {
			URL url = new URL(serviceUrl);
			String urlParameters = "pageName=" + pageName + "&pName" + like;

			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("POST");
			connection.setConnectTimeout(60000); 
			connection.setReadTimeout(60000);  

			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded;charset=UTF-8");
			OutputStreamWriter writer = new OutputStreamWriter(
					connection.getOutputStream());
			writer.write(urlParameters);
			writer.flush();
			String line, retJson = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));

			while ((line = reader.readLine()) != null) {
				retJson += line;
			}
			writer.close();
			reader.close();
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj ;
			ArrayList<UserEntity> page = new ArrayList<UserEntity>();
			Map<String, ArrayList<UserEntity>> map = new HashMap<String, ArrayList<UserEntity>>();
			page.add(new UserEntity(pageName, like , ""));
			System.out.println("Page: "+page);
			map.put("pageName", page);
			return Response.ok(new Viewable("/jsp/pagesPage", map)).build();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return null;
	}
}