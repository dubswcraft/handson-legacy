package com.dubswcraft.tripservicekata.user;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UserTest {

    @Test public void
    shouldReturnTrueWhenUsersAreFriends() throws Exception {
        User John = new User("JOHN");
        User Bob = new User("BOB");

        John.addFriend(Bob);

        assertTrue(John.isFriendsWith(Bob));
    }

}