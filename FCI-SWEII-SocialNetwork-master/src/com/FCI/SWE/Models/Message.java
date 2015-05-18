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

public class Message {
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




}
