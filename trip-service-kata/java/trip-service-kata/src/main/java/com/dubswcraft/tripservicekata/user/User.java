package com.dubswcraft.tripservicekata.user;

import com.dubswcraft.tripservicekata.trip.Trip;

import java.util.ArrayList;
import java.util.List;

public class User {

    private final String name;
    private List<Trip> trips = new ArrayList<Trip>();
    private List<User> friends = new ArrayList<User>();

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void addFriend(User user) {
        friends.add(user);
    }

    public boolean isFriendsWith(User friend) {
        return friends.contains(friend);
    }

    public void addTrip(Trip trip) {
        trips.add(trip);
    }

    public List<Trip> trips() {
        return trips;
    }

}