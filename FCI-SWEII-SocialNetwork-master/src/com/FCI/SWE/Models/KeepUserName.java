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
public class KeepUserName {
	public KeepUserName(){
		
	}
	public static String userName ; 
	private  KeepUserName(String name){
	 
		userName  = name ; 
		
	}
	public static String   getUserName(){
		return userName ; 
	}
	public   void   setUserName(String userName){
		 this.userName = userName ;
	}
	

}
