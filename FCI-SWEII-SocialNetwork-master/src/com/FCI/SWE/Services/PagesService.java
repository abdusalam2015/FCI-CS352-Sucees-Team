package com.FCI.SWE.Services;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.json.simple.JSONObject;

import com.FCI.SWE.Models.KeepUserName;
import com.FCI.SWE.Models.Page;
@Path("/")
@Produces("text/html")
public class PagesService {
	@POST
	@Path("/PagespageService")
	public String pagesPage(@FormParam("pageName") String pageName,
			@FormParam("pName") String searchPage) {
		JSONObject object = new JSONObject();
		//KeepUserName userName = new KeepUserName();
		//UserEntity u = new UserEntity();
		Page u = new Page();
		// // if (user == null && pageName == null ) return null ;
		 if(searchPage != null)u.savePage( searchPage, KeepUserName.getUserName());
		 if(pageName!=null)u.savePage(pageName,  KeepUserName.getUserName());
		 u.savePage(searchPage,  KeepUserName.getUserName());
		
		return object.toString();
	}
}
