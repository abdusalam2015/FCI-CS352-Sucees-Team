package com.FCI.SWE.Controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.beust.jcommander.Parameters;
/*
 * this class connect with database  
 */
@Parameters
/*
 * urlParameters is the database path 
 * serviceUrl : a kind of serevice that connect with database .
 */
public class ConnectionC {
	public ConnectionC(){}
	public String  connect (String urlParameters , String serviceUrl ){
	try {
		URL url = new URL(serviceUrl);
 
		HttpURLConnection connection = (HttpURLConnection) url
				.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setInstanceFollowRedirects(false);
		connection.setRequestMethod("POST");
		connection.setConnectTimeout(60000);
		connection.setReadTimeout(60000);

		connection.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded;charset=UTF-8");
		OutputStreamWriter writer = new OutputStreamWriter(
				connection.getOutputStream());
		writer.write(urlParameters);
		writer.flush();
		String line, retJson = "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));

		while ((line = reader.readLine()) != null) {
			retJson += line;
		}
		writer.close();
		reader.close();

		JSONParser parser = new JSONParser();
		Object obj = parser.parse(retJson);
		JSONObject object = (JSONObject) obj;

		return retJson ;
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;

	
	}
}
	  
