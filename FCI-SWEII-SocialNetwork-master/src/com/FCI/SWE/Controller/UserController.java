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
 * This class contains REST services, also contains action function for web
 * application
 * 
 * @author Mohamed Samir
 * @version 1.0
 * @since 2014-02-12
 *
 */
@Path("/")
@Produces("text/html")


public class UserController {
	/**
	 * Action function to render Signup page, this function will be executed
	 * using url like this /rest/signup
	 * 
	 * @return sign up page
	 */
	@GET
	@Path("/signup")
	public Response signUp() {
		return Response.ok(new Viewable("/jsp/register")).build();
	}
	/**
	 * Action function to render home page of application, home page contains
	 * only signup and login buttons
	 * 
	 * @return enty point page (Home page of this application)
	 */
	@GET
	@Path("/")
	public Response index() {
		return Response.ok(new Viewable("/jsp/entryPoint")).build();
	}
	 
	
	/**
	 * Action function to render login page this function will be executed using
	 * url like this /rest/login
	 * 
	 * @return login page
	 */
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
	/**
	 * Action function to response to signup request, This function will act as
	 * a controller part and it will calls RegistrationService to make
	 * registration
	 * 
	 * @param uname
	 *            provided user name
	 * @param email
	 *            provided user email
	 * @param pass
	 *            provided user password
	 * @return Status string
	 */
	@POST
	@Path("/response")
	@Produces(MediaType.TEXT_PLAIN)
	public String response(@FormParam("uname") String uname,
			@FormParam("email") String email, @FormParam("password") String pass) {
		String serviceUrl = "http://localhost:8888/rest/RegistrationService";
		try {
			URL url = new URL(serviceUrl);
			
			String urlParameters = "uname=" + uname + "&email=" + email
					+ "&password=" + pass;
			
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
			if(uname == null && pass == null && email==null){
				UserEntity user = new UserEntity();
				KeepUserName n = new KeepUserName();
				String s =n.getUserName();
				// serv.getUserName();
				 System.out.println("good man : "+s);
				user.saveUser2(s, "1");
				return "Accepted";
				
			}
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

	/**
	 * Action function to response to login request. This function will act as a
	 * controller part, it will calls login service to check user data and get
	 * user from datastore
	 * 
	 * @param uname
	 *            provided user name
	 * @param pass
	 *            provided user password
	 * @return Home page view
	 */
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
			if (object.get("Status").equals("Failed"))
				return null;
			 Map< String ,  ArrayList<UserEntity>> map = new HashMap<String , ArrayList<UserEntity> >();
		
			UserEntity user = UserEntity.getUser (object.toJSONString());
			ArrayList<UserEntity> requistList = new ArrayList<UserEntity>();
			ArrayList<UserEntity> messageList = new ArrayList<UserEntity>();
			ArrayList<UserEntity> groupMessageList = new ArrayList<UserEntity>();
			
			KeepUserName u = new KeepUserName();
			 
			requistList.addAll(user.searchForReq(u.getUserName()));	
			map.put("friends",requistList);
			messageList.addAll(user.getSenderMessages(u.getUserName()));
			map.put("messages",messageList);
			
			groupMessageList.addAll(user.getGroupMessages(u.getUserName()));
			System.out.println("gh :"+groupMessageList);
			map.put("GMSG", groupMessageList);
			System.out.println("home group message ");
			return Response.ok(new Viewable("/jsp/home",map)).build();
			
		} catch (MalformedURLException e) {
		 
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * UserEntity user = new UserEntity(uname, email, pass);
		 * user.saveUser(); return uname;
		 */
		return null;
	}

	@POST
	@Path("/userspage")
	@Produces("text/html")
	public Response users(@FormParam("uname") String uname,
			@FormParam("password") String pass) {
		String serviceUrl = "http://localhost:8888/rest/UserspageService";
		try {
			URL url = new URL(serviceUrl);
			String urlParameters = "uname=" + uname + "&password=" + pass;
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
			if (object.get("Status").equals("Failed"))
				return null;
			
			Map<String, String> map = new HashMap<String, String>();
			UserEntity user = UserEntity.getUser(object.toJSONString());
			map.put("name", user.getName());
			map.put("email", user.getEmail());
			KeepUserName n = new KeepUserName();
			String s =n.getUserName();
			//serv.getUserName();
			 System.out.println("good man : "+s);
			user.saveUser2(s, "0");
			//user.saveFriend(user.getName(), "users");
			return Response.ok(new Viewable("/jsp/userspage", map)).build();
		} catch (MalformedURLException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * UserEntity user = new UserEntity(uname, email, pass);
		 * user.saveUser(); return uname;
		 */
		return null;
	}
	
	@POST
	@Path("/groupMessage")
	@Produces("text/html")
	public Response groupMessage(@FormParam("groupName") String groupName , @FormParam("memmber") String memmber,
			@FormParam("message") String message) {
		
		String serviceUrl = "http://localhost:8888/rest/GroupMessage" ;
		try {
		    //System.out.println("messageName :"+name + "messages :"+message );
			URL url = new URL(serviceUrl);
			String urlParameters = "groupName=" + groupName +"&memmber=" + memmber+ "&message=" + message  ;
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
			System.out.println("groupName_here : "+ groupName);
			System.out.println("memmber_here : "+ memmber);
			System.out.println("message_here  : "+ message);
			 Map<String , ArrayList <UserEntity> > map = new HashMap<String , ArrayList<UserEntity> >();
			 UserEntity user = new UserEntity();
			ArrayList<UserEntity> requistList = new ArrayList<UserEntity>();
			ArrayList<UserEntity> groupMessageList = new ArrayList<UserEntity>();
			KeepUserName u = new KeepUserName();
			groupMessageList=user.getGroupMessages(u.getUserName()) ;
			map.put("GMSG",groupMessageList);
			
			return Response.ok(new Viewable("/jsp/groupMessage",map)).build();
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
		/*
		 * UserEntity user = new UserEntity(uname, email, pass);
		 * user.saveUser(); return uname;
		 */
		return null;
	}
	@POST
	@Path("/message")
	@Produces("text/html")
	public Response message( 	@FormParam("name") String name,	@FormParam("message") String message) {
		
		String serviceUrl = "http://localhost:8888/rest/MessageService" ;
		try {
			//System.out.println("messageName :"+name + "messages :"+message );
			URL url = new URL(serviceUrl);
			String urlParameters = "name=" + name +"&message=" + message;
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
		 
			 Map< String ,  ArrayList <UserEntity> > map = new HashMap< String  , ArrayList<UserEntity>>();
			 UserEntity user = new UserEntity();
			ArrayList<UserEntity> messageList = new ArrayList<UserEntity>();
			ArrayList<UserEntity> messageList2 = new ArrayList<UserEntity>();
			KeepUserName u = new KeepUserName();
			if(name == null ){
				messageList.addAll(user.getMessages(u.getUserName()));
				map.put("messages",messageList);
			}else {
				messageList2.addAll(user.getSpecificMessages(u.getUserName(), name));
				map.put("messages",messageList2);
			}
			return Response.ok(new Viewable("/jsp/message",map)).build();
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
		/*
		 * UserEntity user = new UserEntity(uname, email, pass);
		 * user.saveUser(); return uname;
		 */
		return null;
	}
}