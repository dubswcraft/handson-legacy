package com.dubswcraft.tripservicekata.trip;

import com.dubswcraft.tripservicekata.exception.CollaboratorCallException;
import com.dubswcraft.tripservicekata.user.User;

import java.util.List;

public class TripDAO {

	public static List<Trip> findTripsByUser(User user) {
		throw new CollaboratorCallException(
				"TripDAO should not be invoked on an unit test.");
	}
	
}