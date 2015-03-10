package com.FCI.SWE.Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
	public UserEntity( ) {//AA
		this.name = "";
		this.email = "";
		this.password = "";

	}
	 
	public UserEntity(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;

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

	public static UserEntity getUser( String name, String pass) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
        
		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			
			if ( entity.getProperty("name").toString().equals(name) 
					&& entity.getProperty("password").toString().equals(pass) ) {
				UserEntity returnedUser = new UserEntity(entity.getProperty(
						"name").toString(), entity.getProperty("email")
						.toString(), entity.getProperty("password").toString());
				return returnedUser;
			}
		}

		return null;
	}
	public static UserEntity getUserOnly(String name ) {//AA
		 
			DatastoreService datastore = DatastoreServiceFactory
					.getDatastoreService();
			Query gaeQuery = new Query("users");
			PreparedQuery pq = datastore.prepare(gaeQuery);
			for (Entity entity : pq.asIterable()) {
				if ( entity.getProperty("name").toString().equals(name)) {
					UserEntity returnedUser = new UserEntity(entity.getProperty(
					     "name").toString(), entity.getProperty("email")
							.toString(), entity.getProperty("password").toString());
					
					return returnedUser;
				}
			}

			return null;
		}
	public static ArrayList<UserEntity> searchForReq(String name ) {//AA
		 
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("friends");
		
		PreparedQuery pq = datastore.prepare(gaeQuery);
		UserEntity returnedUser = null ;
		
		ArrayList<UserEntity>requestList = new ArrayList<>(); 
		
		for (Entity entity : pq.asIterable()) {
			if ( entity.getProperty("userName").toString().equals(name) &&
					entity.getProperty("accepted").toString().equals("0")) {
				
				 returnedUser = new UserEntity(entity.getProperty("requisted").toString() ,
						 entity.getProperty("userName")
							.toString() ,entity.getProperty(
				     "accepted").toString());
				 System.out.println("fun :-"+returnedUser.getName());
				 requestList.add(returnedUser);
				
			}
		}
		return requestList ; 

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
	public Boolean saveUser2(String reqName,String flag) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
//		Query gaeQuery = new Query("friends");
//		PreparedQuery pq = datastore.prepare(gaeQuery);
//		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		Entity employee = new Entity("friends" );
		employee.setProperty("userName",   reqName);
		employee.setProperty("requisted",this.getName());
		employee.setProperty("accepted",flag);
		//employee.setProperty("password", this.password);
		datastore.put(employee);
		return true;
	}

	
	
public boolean saveFriend(String name ,String table) {
	DatastoreService datastore = DatastoreServiceFactory
			.getDatastoreService();
	 
	Query gaeQuery = new Query(table);
	PreparedQuery pq = datastore.prepare(gaeQuery);
	List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
	Entity employee = new Entity(table);
		UserEntity returnedUser =  null;
		for (Entity entity : pq.asIterable()) {
			
			if ( entity.getProperty("name").toString().equals(name)) {
					 
					return true;	 
		}
	}
		return false ;
}
}