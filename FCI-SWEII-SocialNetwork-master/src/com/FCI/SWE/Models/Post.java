package com.FCI.SWE.Models;

import java.util.ArrayList;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class Post {
	/**
	 * 
	 * @param name : user who write this post 
	 * @param post : body of post
	 * @param privacy : privacy equal (1) if it's else (0)
	 * @param pub : public post if it's value = 1 else = 0 
	 * @return true when it's stor in database correctly 
	 */
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
	/**
	 * 
	 * @param uname : user name 
	 * @return all posts that you and your friend are  write    
	 */
	public static ArrayList<UserEntity> getPosts(String uname) {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		ArrayList<UserEntity> posts = new ArrayList<>();
		Query gaeQuery = new Query("posts");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		UserEntity obj2 = new UserEntity();
		Friend f = new Friend ();
		String friends = f.getFriends(uname);
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
	/**
	 * 
	 * @param hashTagName
	 * @return all hash tags that same name 
	 */
	public static ArrayList<UserEntity> getHashTag(String hashTagName) {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		ArrayList<UserEntity> hashTagList = new ArrayList<>();
		Query gaeQuery = new Query("hashTags");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		UserEntity obj2 = new UserEntity();
		 
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("hashTag").toString().equals(hashTagName) ) {
				 
			UserEntity obj = new UserEntity(entity.getProperty("post")
					.toString(), entity.getProperty("hashTag").toString(),
					  entity.getProperty("userName").toString());
			System.out.println("hhhhh "+entity.getProperty("post")
					.toString());
			hashTagList.add(obj);
				}

			}
		
		return hashTagList;
	}

/**
 * 
 * @param uname : user Name
 * @return get all post that it's privacy only 
 */
	
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
	/**
	 * 
	 * @param uname : userName 
	 * @param hashtage : hash tag name 
	 * @param post that has #hashtage
	 * @return true when the post store in hashtage table in database 
	 */
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

}
