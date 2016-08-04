package com.dubswcraft.tripservicekata;

import com.dubswcraft.tripservicekata.trip.Trip;
import com.dubswcraft.tripservicekata.trip.TripService;
import com.dubswcraft.tripservicekata.user.User;
import com.github.approval.Approvals;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class TripService_GoldenMaster {

    private static final User LOGGED_USER = new User("LOGGED_USER");
    private static User PAUL = new User("PAUL");
    private static User JOE = new User("JOE");
    private static User DEBORAH = new User("DEBORAH");
    private static User SARAH = new User("SARAH");


    @Before
    public void setup() {
        JOE.addFriend(LOGGED_USER);
        JOE.addFriend(PAUL);

        PAUL.addFriend(LOGGED_USER);
        PAUL.addFriend(DEBORAH);

        SARAH.addFriend(LOGGED_USER);
        SARAH.addFriend(DEBORAH);

        DEBORAH.addFriend(SARAH);
    }

    @Test
    public void golden_master() {
        assertEquals(true, true);
        TripService_Original tripService = new TripService_Original();
        User paul = new User("PAUL");
        paul.addFriend(LOGGED_USER);
        List<Trip> tripsForJoe = tripService.getTripsByUser(JOE);
        List<Trip> tripsForPaul = tripService.getTripsByUser(PAUL);
        List<Trip> tripsForDeborah = tripService.getTripsByUser(DEBORAH);
        List<Trip> tripsForSarah = tripService.getTripsByUser(SARAH);

        StringBuilder allTripsForUsers = new StringBuilder();
        allTripsForUsers.append(getStringRepresentationFor(JOE, tripsForJoe));
        allTripsForUsers.append(getStringRepresentationFor(PAUL, tripsForPaul));
        allTripsForUsers.append(getStringRepresentationFor(DEBORAH, tripsForDeborah));
        allTripsForUsers.append(getStringRepresentationFor(SARAH, tripsForSarah));
        Approvals.verify(allTripsForUsers.toString(), Paths.get("src", "resources", "approval", "result.txt"));
    }

    private String getStringRepresentationFor(User user, List<Trip> trips) {
        StringBuilder builder = new StringBuilder();
        builder.append(user.getName()).append("'s trips : ").append("\r");
        for (Trip trip : trips) {
            builder.append(trip.getTripName()).append("\r");
        }
        builder.append("\n\n");
        return builder.toString();
    }
}
