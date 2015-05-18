package com.FCI.SWE.Models;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class Page {

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

}
