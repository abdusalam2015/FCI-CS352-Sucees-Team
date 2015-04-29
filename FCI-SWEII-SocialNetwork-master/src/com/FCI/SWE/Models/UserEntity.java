package com.FCI.SWE.Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.storage.onestore.v3.OnestoreEntity.User;

/**
 * @author Kamal Alhusam
 * @author Islam imam
 * @author Hamed alghazaly
 * @author Abdussalam Al-shouiby
 * @version 1.0
 * @since 2015-03-12
 */
public class UserEntity {

	public String name;
	public String email;
	public String password;
	public String live;
	public String birthdate;
	public String student;
	public String nationality;

	public UserEntity() { 
		this.name = "";
		this.email = "";
		this.password = "";
		this.live = "";
		this.birthdate = "";
		this.student = "";
		this.nationality = "";

	}

	public UserEntity(String name, String email, String passwotd, String live,
			String birthdate, String student, String nationality) {
		this.name = name;
		this.email = email;
		this.password = passwotd;
		this.live = live;
		this.birthdate = birthdate;
		this.student = student;
		this.nationality = nationality;

	}

	public UserEntity(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public UserEntity(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPass() {
		return password;
	}

	public String getLive() {
		return live;
	}

	public String getStudent() {
		return student;
	}

	public String getNationality() {
		return nationality;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public static UserEntity getUser(String json) {

		JSONParser parser = new JSONParser();
		try {
			JSONObject object = (JSONObject) parser.parse(json);
			return new UserEntity(object.get("name").toString(), object.get(
					"email").toString(), object.get("password").toString());
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return null;

	}

	public static UserEntity getUser(String name, String pass) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {

			if (entity.getProperty("name").toString().equals(name)
					&& entity.getProperty("password").toString().equals(pass)) {
				UserEntity returnedUser = new UserEntity(entity.getProperty(
						"name").toString(), entity.getProperty("email")
						.toString(), entity.getProperty("password").toString());
				return returnedUser;
			}
		}

		return null;
	}

	public static UserEntity getUserOnly(String name) {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("name").toString().equals(name)) {
				UserEntity returnedUser = new UserEntity(entity.getProperty(
						"name").toString(), entity.getProperty("email")
						.toString(), entity.getProperty("password").toString());

				return returnedUser;
			}
		}

		return null;
	}

	public static UserEntity getpage(String pageName) {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("pages");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("pageName").toString().equals(pageName)) {
				UserEntity returnedUser = new UserEntity(entity.getProperty(
						"pageName").toString(), entity.getProperty("likes")
						.toString(), entity.getProperty("password").toString());

				return returnedUser;
			}
		}

		return null;
	}

	public static ArrayList<UserEntity> getGroupMessages(String userName) {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("conversations");

		PreparedQuery pq = datastore.prepare(gaeQuery);
		String str = "";
		ArrayList<UserEntity> messageList = new ArrayList<>();
		for (Entity entity : pq.asIterable()) {

			if (entity.getProperty("memmbers").toString().equals(userName)
					|| entity.getProperty("sender").toString().equals(userName)) {
				str = entity.getProperty("groupName").toString();
				for (Entity entity2 : pq.asIterable()) {
					if (str.equals(entity2.getProperty("groupName").toString())) {
						UserEntity returnedUser = new UserEntity(entity2
								.getProperty("message").toString(), entity2
								.getProperty("sender").toString(), entity2
								.getProperty("groupName").toString());
						messageList.add(returnedUser);
					}
					break;

				}
			}
		}

		return messageList;
	}

	public static ArrayList<UserEntity> searchForReq(String name) {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("friends");

		PreparedQuery pq = datastore.prepare(gaeQuery);
		UserEntity returnedUser = null;

		ArrayList<UserEntity> requestList = new ArrayList<>();

		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("requisted").toString().equals(name)
					&& entity.getProperty("accepted").toString().equals("0")
					&& entity.getProperty("userName").toString() != name) {

				returnedUser = new UserEntity(entity.getProperty("userName").toString() );

				requestList.add(returnedUser);

			}
		}
		return requestList;
	}

	public static ArrayList<UserEntity> getMessages(String userName) {// AA

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("messages");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		UserEntity returnedUser = null;

		ArrayList<UserEntity> messageList = new ArrayList<>();

		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("receiver").toString().equals(userName)) {
				returnedUser = new UserEntity(entity.getProperty("message")
						.toString(), entity.getProperty("receiver").toString(),
						entity.getProperty("sender").toString());
				messageList.add(returnedUser);

			}
		}
		return messageList;
	}

	public static ArrayList<UserEntity> getconversations(String userName) {// AA

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("conversations");

		PreparedQuery pq = datastore.prepare(gaeQuery);

		UserEntity returnedUser = null;

		ArrayList<UserEntity> messageList = new ArrayList<>();

		for (Entity entity : pq.asIterable()) {

			if (entity.getProperty("memmbers").toString().equals(userName)
					|| entity.getProperty("sender").toString().equals(userName)) {
				String str = entity.getProperty("groupName").toString();
				for (Entity entity2 : pq.asIterable()) {
					returnedUser = new UserEntity(entity2
							.getProperty("message").toString(), entity2
							.getProperty("sender").toString(), entity2
							.getProperty("groupName").toString());

					messageList.add(returnedUser);

				}
				break;

			}
		}

		return messageList;
	}

	public static ArrayList<UserEntity> getSpecificMessages(String userName,
			String name) {// AA

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("messages");

		PreparedQuery pq = datastore.prepare(gaeQuery);
		UserEntity returnedUser = null;

		ArrayList<UserEntity> messageList = new ArrayList<>();

		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("receiver").toString().equals(userName)
					&& entity.getProperty("sender").toString().equals(name)
					|| entity.getProperty("receiver").toString().equals(name)
					&& entity.getProperty("sender").toString().equals(userName)) {

				returnedUser = new UserEntity(entity.getProperty("message")
						.toString(), entity.getProperty("receiver").toString(),
						entity.getProperty("sender").toString());

				messageList.add(returnedUser);

			}
		}
		return messageList;
	}

	public static ArrayList<UserEntity> getNotificationGMSG(String userName) {// AA

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("conversations");

		PreparedQuery pq = datastore.prepare(gaeQuery);
		UserEntity returnedUser = null;

		ArrayList<UserEntity> conversationList = new ArrayList<>();
		ArrayList<UserEntity> newConversationList = new ArrayList<>();
		HashSet<String> mySet = new HashSet<>();

		for (Entity entity : pq.asIterable()) {

			if (entity.getProperty("memmbers").toString().equals(userName)
					|| entity.getProperty("sender").toString().equals(userName)) {

				returnedUser = new UserEntity(entity.getProperty("groupName")
						.toString(), entity.getProperty("message").toString(),
						entity.getProperty("sender").toString());
				mySet.add(entity.getProperty("groupName").toString());
				conversationList.add(returnedUser);

			}
		}

		int counter = 0;
		for (String gName : mySet) {
			for (int i = 0; i < conversationList.size(); i++) {
				if (conversationList.get(i).getName().equals(gName)) {
					counter++;
				}
			}
			String messageNumber = counter + "";
			newConversationList.add(new UserEntity(gName, messageNumber, ""));
			counter = 0;
		}
		return newConversationList;
	}

	public static ArrayList<UserEntity> getSenderMessages(String userName) {// AA

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("messages");

		PreparedQuery pq = datastore.prepare(gaeQuery);
		UserEntity returnedUser = null;

		ArrayList<UserEntity> messageList = new ArrayList<>();
		ArrayList<UserEntity> newMessageList = new ArrayList<>();
		HashSet<String> mySet = new HashSet<>();
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("receiver").toString().equals(userName)) {

				returnedUser = new UserEntity(entity.getProperty("sender")
						.toString(), entity.getProperty("receiver").toString(),
						entity.getProperty("message").toString());
				mySet.add(entity.getProperty("sender").toString());
				messageList.add(returnedUser);

			}
		}

		int counter = 0;
		for (String senderName : mySet) {
			for (int i = 0; i < messageList.size(); i++) {
				if (messageList.get(i).getName().equals(senderName)) {
					counter++;
				}
			}
			String messageNumber = counter + "";
			newMessageList.add(new UserEntity(senderName, messageNumber, ""));
			counter = 0;
		}
		return newMessageList;
	}

	public Boolean saveUser() {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		Entity employee = new Entity("users");
		employee.setProperty("name", this.name);
		employee.setProperty("email", this.email);
		employee.setProperty("password", this.password);
		employee.setProperty("student", this.student);
		employee.setProperty("live", this.live);
		employee.setProperty("birthdate", this.birthdate);
		employee.setProperty("nationality", this.nationality);
		datastore.put(employee);
		return true;
	}

	public Boolean saveUser2(String reqName, String flag , String uname) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Entity employee = new Entity("friends");
		employee.setProperty("userName", reqName);
		employee.setProperty("requisted", uname);
		employee.setProperty("accepted", flag);
		datastore.put(employee);
		return true;
	}
	public Boolean saveFriends(String reqName, String flag  ) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Entity employee = new Entity("friends");
		employee.setProperty("userName", reqName);
		employee.setProperty("requisted", this.getName());
		employee.setProperty("accepted", flag);
		datastore.put(employee);
		return true;
	}

	public Boolean savePage(String pageName, String like) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Entity employee = new Entity("pages");
		employee.setProperty("pageName", pageName);
		employee.setProperty("like", like);
		employee.setProperty("id", like);

		datastore.put(employee);
		return true;
	}

	public Boolean saveMessage(String receive, String message) {

		KeepUserName username = new KeepUserName();
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("messages");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		Entity employee = new Entity("messages");
		employee.setProperty("sender", username.getUserName());
		employee.setProperty("receiver", receive);
		employee.setProperty("message", message);
		datastore.put(employee);
		return true;
	}

	public static UserEntity getGroupName(String name) {// AA

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("conversations");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("groupName").toString().equals(name)) {
				UserEntity returnedUser = new UserEntity(entity.getProperty(
						"groupName").toString(), entity.getProperty("sender")
						.toString(), entity.getProperty("message").toString());

				return returnedUser;
			}
		}

		return null;
	}

	public static UserEntity isMemmber(String userName) {// AA
		if (userName == "") {
			return null;
		}
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("conversations");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("memmbers").toString().equals(userName)) {
				UserEntity returnedUser = new UserEntity(entity.getProperty(
						"message").toString(), entity.getProperty("sender")
						.toString(), entity.getProperty("groupName").toString());

				return returnedUser;
			}
		}

		return null;
	}

	public Boolean createGroupMessage(String name, String memmber,
			String message) {

		KeepUserName username = new KeepUserName();
		UserEntity user = new UserEntity();
		user = user.isMemmber(username.getUserName());
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("conversations");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		Entity employee = new Entity("conversations");
		employee.setProperty("sender", username.getUserName());
		employee.setProperty("groupName", name);
		employee.setProperty("message", message);
		employee.setProperty("memmbers", memmber);
		datastore.put(employee);

		return true;
	}

	public Boolean deleteEntity(String reqName, String flag) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Entity employee = new Entity("friends");
		employee.removeProperty("userName");
		employee.removeProperty("requisted");
		employee.removeProperty("accepted");

		datastore.put(employee);
		return true;
	}
	public Boolean writePost(String name, String post , String privacy , String pub) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Entity employee = new Entity("posts");
		KeepUserName obj = new KeepUserName();
		employee.setProperty("userName", obj.getUserName());
		employee.setProperty("post", post);
		employee.setProperty("comments", "YES");
		employee.setProperty("likes", "OK");
		employee.setProperty("public", pub);
		employee.setProperty("privacy", privacy);
		datastore.put(employee);
		return true;
	}


	public static ArrayList<UserEntity> getPosts(String uname) {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		ArrayList<UserEntity> posts = new ArrayList<>();
		Query gaeQuery = new Query("posts");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		UserEntity obj2 = new UserEntity();
		String friends = getFriends(uname);
		System.out.println("Friends : "+friends);
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("userName").toString().equals(uname)  ||
					friends.contains(entity.getProperty("userName").toString())  ) {
				if(entity.getProperty("public").toString().equals("1") ){
			UserEntity obj = new UserEntity(entity.getProperty("post")
					.toString(), entity.getProperty("userName").toString(),
					  entity.getProperty("likes").toString());
				posts.add(obj);
				}

			}
		}
		return posts;
	}
	public static ArrayList<UserEntity> getPrivacyPosts(String uname) {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		ArrayList<UserEntity> privacyPosts = new ArrayList<>();
		Query gaeQuery = new Query("posts");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		UserEntity obj2 = new UserEntity();
		 
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("userName").toString().equals(uname)  &&
					entity.getProperty("privacy").toString().equals("1") ) {
				 
			UserEntity obj = new UserEntity(entity.getProperty("post")
					.toString(), entity.getProperty("userName").toString(),
					  entity.getProperty("likes").toString());
			privacyPosts.add(obj);
				}

			}
		
		return privacyPosts;
	}

	public static String getFriends(String userName) {// AA

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("friends");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		UserEntity returnedUser = null;
		String friends = "";

		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("userName").toString().equals(userName)
					||entity.getProperty("requisted").toString().equals(userName)
					&& entity.getProperty("accepted").toString().equals("1")) {
				friends += entity.getProperty("requisted").toString();
				friends += " ";

			}
		}
		return friends;
	}

	public Boolean writeHashTag(String uname, String hashtage, String post) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Entity employee = new Entity("hashTags");
		employee.setProperty("userName", uname);
		employee.setProperty("hashTag", hashtage);
		employee.setProperty("post", post);
		datastore.put(employee);
		return true;
	}

	public static ArrayList<UserEntity> getTimeLine(String name) {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		ArrayList<UserEntity> timeLine = new ArrayList<>();
		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("name").toString().equals(name)) {

				UserEntity obj = new UserEntity(entity.getProperty("name")
						.toString(), entity.getProperty("email").toString(),
						"password", entity.getProperty("live").toString(),
						entity.getProperty("birthdate").toString(), entity
								.getProperty("student").toString(), entity
								.getProperty("nationality").toString());
				timeLine.add(obj);
				return timeLine;

			}
		}
		return null;
	}
	
	public static ArrayList<UserEntity> getHashTag(String hashTagName) {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		ArrayList<UserEntity> hashTagList = new ArrayList<>();
		Query gaeQuery = new Query("hashTags");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		UserEntity obj2 = new UserEntity();
		 
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("userName").toString().equals(hashTagName) ) {
				 
			UserEntity obj = new UserEntity(entity.getProperty("post")
					.toString(), entity.getProperty("hashTag").toString(),
					  entity.getProperty("userName").toString());
			hashTagList.add(obj);
				}

			}
		
		return hashTagList;
	}


}