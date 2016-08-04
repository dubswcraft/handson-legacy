package com.dubswcraft.tripservicekata.trip;

import com.dubswcraft.tripservicekata.exception.UserNotLoggedInException;
import com.dubswcraft.tripservicekata.user.User;

import java.util.ArrayList;
import java.util.List;

public class TripService {

    public List<Trip> getFriendTrips(User loggedUser, User friend) throws UserNotLoggedInException {
        validate(loggedUser);
        return (friend.isFriendsWith(loggedUser))
                ? findTripsForFriend(friend)
                : new ArrayList<Trip>();
    }

    private void validate(User loggedUser) throws UserNotLoggedInException {
        if (loggedUser == null) throw new UserNotLoggedInException();
    }

    protected List<Trip> findTripsForFriend(User friend) {
        return TripDAO.findTripsByUser(friend);
    }
}
