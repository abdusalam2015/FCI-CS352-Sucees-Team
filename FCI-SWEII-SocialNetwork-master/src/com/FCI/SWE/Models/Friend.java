package com.FCI.SWE.Models;

import java.util.ArrayList;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class Friend {
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
		KeepUserName obj = new KeepUserName();
		Entity employee = new Entity("friends");
		employee.setProperty("userName", reqName);
		employee.setProperty("requisted", obj.getUserName());
		employee.setProperty("accepted", flag);
		datastore.put(employee);
		return true;
	}


}
