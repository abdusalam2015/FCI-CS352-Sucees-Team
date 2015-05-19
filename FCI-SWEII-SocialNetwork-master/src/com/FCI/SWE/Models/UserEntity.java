
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
public class UserEntity   {

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
/**
 * 
 * @param json : user name
 * @return username and email if is found in database else return null 
 */
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
/**
 * 
 * @param name
 * @param pass
 * @return username and email if is found in database else return null 
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
/**
 * 
 * @param name
 * @return user name only from database 
 */
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
/**
 * 
 * @return true if registration successfully 
 */
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
	

/**
 * 
 * @param name : username 
 * @return list from users table by all information that is related to this user name 
 */
	
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
	
	

	
/**
 * 
 * @param userName : group name 
 * @return true if the group is created   , false if it is not found in database  
 */
	public static boolean  isMemmber(String userName) {// AA
		 
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("conversations");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("memmbers").toString().equals(userName)) {
				UserEntity returnedUser = new UserEntity(entity.getProperty(
						"message").toString(), entity.getProperty("sender")
						.toString(), entity.getProperty("groupName").toString());

				return true ;
			}
		}

		return false ;
	}
	
}