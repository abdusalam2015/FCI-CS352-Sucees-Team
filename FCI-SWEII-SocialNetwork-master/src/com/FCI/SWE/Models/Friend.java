package com.FCI.SWE.Models;

import java.util.ArrayList;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class Friend {
	
	/**
	 * 
	 * @param userName
	 * @return all userName Friends from database 
	 */
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
	/**
	 * 
	 * @param name
	 * @return a list of all requests that not be accepted from username : name
	 */
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
	/**
	 * 
	 * @param reqName person that  friend request from uname  
	 * @param flag  : flage = 1 if is accept , flage = 0 if is not accepted
	 * @param uname person who recive friend request
	 * @return true if the recive in database .
	 */
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
