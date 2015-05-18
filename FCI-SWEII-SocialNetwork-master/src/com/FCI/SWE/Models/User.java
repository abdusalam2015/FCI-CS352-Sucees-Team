package com.FCI.SWE.Models;

import java.util.ArrayList;

public interface  User {
	public   ArrayList<UserEntity> getGroupMessages(String userName , String gName );
	public   ArrayList<UserEntity> getconversations(String userName);
	public   ArrayList<UserEntity> getNotificationGMSG(String userName);
	public Boolean createGroupMessage(String name, String memmber,String message);
	public   UserEntity getGroupName(String name) ;
	public   UserEntity getUser(String name, String pass);

}
