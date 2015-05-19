package com.FCI.SWE.Models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class GroupMessage implements User{
	
	/*
	 * @param userName : a person who member in  a group 
	 * @param gName : group name ;
	 * @return All messages that related by this group .
	 */
	public ArrayList<UserEntity> getGroupMessages(String userName , String gName ) {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("conversations");

		PreparedQuery pq = datastore.prepare(gaeQuery);
		String str = "";
		ArrayList<UserEntity> messageList = new ArrayList<>();
		UserEntity obj = new UserEntity();
		boolean check = obj.isMemmber(userName);
		System.out.println(check  +"check ");
		if(check) {
				for (Entity entity2 : pq.asIterable()) {
					//if (!str.equals(entity2.getProperty("message").toString().equals(null)) ){
						UserEntity returnedUser = new UserEntity(entity2
								.getProperty("message").toString(), entity2
								.getProperty("sender").toString(), entity2
								.getProperty("groupName").toString());
						messageList.add(returnedUser);
						
					//}
					

				}
			}
		
		return messageList;
	}

	public   ArrayList<UserEntity> getconversations(String userName) {// AA

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
/**
 * @param userName is a user name that is a member in the group(s)
 * @return list of notifications that related by messages that send to this group with counter of messages ;
 */
	public   ArrayList<UserEntity> getNotificationGMSG(String userName) {// AA

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
/**
 * @param name : group name  
 * @param member : this attribute will be null if you in create group  of when send message
 * and not be null when add member .
 * @param message : body of message that will be send to  name of group
 * @return true if the group is created successfully 
 */
	public Boolean createGroupMessage(String name, String memmber,
			String message) {

		KeepUserName username = new KeepUserName();
		UserEntity user = new UserEntity();
		boolean check  = user.isMemmber(username.getUserName());
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
	/**
	 * @param name: user name 
	 * @return user name if is member in group 
	 */
	public   UserEntity getGroupName(String name) {// AA

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

	@Override
	public UserEntity getUser(String name, String pass) {
		// TODO Auto-generated method stub
		return null;
	}



}
