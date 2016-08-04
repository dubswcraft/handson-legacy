package com.dubswcraft.tripservicekata.user;


import com.dubswcraft.tripservicekata.exception.CollaboratorCallException;

public class UserSession {

	private static final UserSession userSession = new UserSession();
	
	private UserSession() {
	}
	
	public static UserSession getInstance() {
		return userSession;
	}

	public User getLoggedUser() {
		return new User("LOGGED_USER");
	}

}
