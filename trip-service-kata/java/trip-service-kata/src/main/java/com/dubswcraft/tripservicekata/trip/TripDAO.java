package com.dubswcraft.tripservicekata.trip;

import com.dubswcraft.tripservicekata.user.User;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Map;

public class TripDAO {

    public final static Trip LONDON = new Trip("LONDON");
    public final static Trip MILAN = new Trip("MILAN");
    public final static Trip NEW_YORK = new Trip("NEW_YORK");
    public final static Trip SINGAPORE = new Trip("SINGAPORE");
    public final static Trip TOKYO = new Trip("TOKYO");
    public final static Trip SYDNEY = new Trip("SYDNEY");

    private final static ArrayList tripsForPaul = Lists.newArrayList(LONDON, MILAN, SYDNEY);
    private final static ArrayList tripsForSarah = Lists.newArrayList(MILAN, NEW_YORK, SINGAPORE, TOKYO);
    private final static ArrayList tripsForJoe = Lists.newArrayList(MILAN);
    private final static ArrayList tripsForDeborah = Lists.newArrayList(LONDON, NEW_YORK, SINGAPORE, TOKYO, SYDNEY);

    private static Map<String, ArrayList> userDB = new ImmutableMap.Builder<String, ArrayList>()
            .put("JOE", tripsForJoe)
            .put("SARAH", tripsForSarah)
            .put("PAUL", tripsForPaul)
            .put("DEBORAH", tripsForDeborah)
            .build();

	public static ArrayList<Trip> findTripsByUser(User user) {
		return userDB.get(user.getName());
	}
	
}