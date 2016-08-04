package com.dubswcraft.tripservicekata;

import com.dubswcraft.tripservicekata.exception.UserNotLoggedInException;
import com.dubswcraft.tripservicekata.trip.Trip;
import com.dubswcraft.tripservicekata.trip.TripDAO;
import com.dubswcraft.tripservicekata.user.User;
import com.dubswcraft.tripservicekata.user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService_Original {

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		List<Trip> tripList = new ArrayList<Trip>();
		User loggedUser = UserSession.getInstance().getLoggedUser();
		boolean isFriend = false;
		if (loggedUser != null) {
			for (User friend : user.getFriends()) {
				if (friend.getName().equals(loggedUser.getName())) {
					isFriend = true;
					break;
				}
			}
			if (isFriend) {
				tripList = TripDAO.findTripsByUser(user);
			}
			return tripList;
		} else {
			throw new UserNotLoggedInException();
		}
	}
	
}
