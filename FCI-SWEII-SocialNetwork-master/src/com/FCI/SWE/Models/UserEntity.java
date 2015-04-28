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

/**
 * <h1>User Entity class</h1>
 * <p>
 * This class will act as a model for user, it will holds user data
 * </p>
 *
 * @author Mohamed Samir
 * @version 1.0
 * @since 2014-02-12
 */
public class UserEntity {
	private String name;
	private String email;
	private String password;

	/**
	 * Constructor accepts user data
	 * 
	 * @param name
	 *            user name
	 * @param email
	 *            user email
	 * @param password
	 *            user provided password
	 */
	public UserEntity() {// AA
		this.name = "";
		this.email = "";
		this.password = "";

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

	/**
	 * 
	 * This static method will form UserEntity class using json format contains
	 * user data
	 * 
	 * @param json
	 *            String in json format contains user data
	 * @return Constructed user entity
	 */
	public static UserEntity getUser(String json) {

		JSONParser parser = new JSONParser();
		try {
			JSONObject object = (JSONObject) parser.parse(json);
			return new UserEntity(object.get("name").toString(), object.get(
					"email").toString(), object.get("password").toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 
	 * This static method will form UserEntity class using user name and
	 * password This method will search for user in datastore
	 * 
	 * @param name
	 *            user name
	 * @param pass
	 *            user password
	 * @return Constructed user entity
	 */

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

	public static UserEntity getUserOnly(String name) {// AA

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

	public static ArrayList<UserEntity> getGroupMessages(String userName) {// AA

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("conversations");

		PreparedQuery pq = datastore.prepare(gaeQuery);

		System.out.println("islam");
		String str = "";
		ArrayList<UserEntity> messageList = new ArrayList<>();
		for (Entity entity : pq.asIterable()) {
			System.out.println("islam1");
			if (entity.getProperty("memmbers").toString().equals(userName)
					|| entity.getProperty("sender").toString().equals(userName)) {
				str = entity.getProperty("groupName").toString();
				System.out.println("islam2");

				for (Entity entity2 : pq.asIterable()) {
					if (str.equals(entity2.getProperty("groupName").toString())) {
						UserEntity returnedUser = new UserEntity(entity2
								.getProperty("message").toString(), entity2
								.getProperty("sender").toString(), entity2
								.getProperty("groupName").toString());
						messageList.add(returnedUser);
						System.out.println("islam3" + messageList);

					}
					break;

				}
			}
		}

		return messageList;
	}

	public static ArrayList<UserEntity> searchForReq(String name) {// AA

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

				returnedUser = new UserEntity(entity.getProperty("userName")
						.toString());
				System.out.println("fun :-" + returnedUser.getName());
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
				System.out.println("mmmge  :-" + returnedUser.getName());
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
				System.out.println("mmmge  :-" + returnedUser.getName());
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

				System.out.println("mmmge  :-" + returnedUser.getName());

				mySet.add(entity.getProperty("groupName").toString());
				conversationList.add(returnedUser);

			}
		}

		int counter = 0;
		for (String gName : mySet) {
			System.out.println("tomoro2 :" + gName);

			for (int i = 0; i < conversationList.size(); i++) {

				System.out.println("tomoro :"
						+ conversationList.get(i).getName());
				if (conversationList.get(i).getName().equals(gName)) {
					counter++;
				}
			}
			String messageNumber = counter + "";
			System.out.println("str : " + messageNumber + "int: " + counter);
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
				System.out.println("mmmge  :-" + returnedUser.getName());
				mySet.add(entity.getProperty("sender").toString());
				messageList.add(returnedUser);

			}
		}

		int counter = 0;
		for (String senderName : mySet) {
			System.out.println("tomoro2 :" + senderName);

			for (int i = 0; i < messageList.size(); i++) {

				System.out.println("tomoro :" + messageList.get(i).getName());
				if (messageList.get(i).getName().equals(senderName)) {
					counter++;
				}
			}
			String messageNumber = counter + "";
			System.out.println("str : " + messageNumber + "int: " + counter);
			newMessageList.add(new UserEntity(senderName, messageNumber, ""));
			counter = 0;
		}
		return newMessageList;
	}

	/**
	 * This method will be used to save user object in datastore
	 * 
	 * @return boolean if user is saved correctly or not
	 */

	public Boolean saveUser() {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		Entity employee = new Entity("users", list.size() + 1);
		employee.setProperty("name", this.name);
		employee.setProperty("email", this.email);
		employee.setProperty("password", this.password);
		datastore.put(employee);

		return true;

	}

	public Boolean saveUser2(String reqName, String flag) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		// Query gaeQuery = new Query("friends");
		// PreparedQuery pq = datastore.prepare(gaeQuery);
		// List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		Entity employee = new Entity("friends");
		employee.setProperty("userName", reqName);
		employee.setProperty("requisted", this.getName());
		employee.setProperty("accepted", flag);
		// employee.setProperty("password", this.password);
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

		System.out.println("database1 ");

		KeepUserName username = new KeepUserName();
		UserEntity user = new UserEntity();
		user = user.isMemmber(username.getUserName());
		System.out.println("here is user KKK :" + user + " MSG :" + message
				+ "Gr ;" + name);
		// if(user.equals(null) && message!=null && name.equals(null )) return
		// true ;
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
		System.out.println("database ");
		datastore.put(employee);
		System.out.println("database4 ");
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
}