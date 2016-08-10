package com.dubswcraft.tripservicekata

import java.nio.file.Paths

import com.dubswcraft.tripservicekata.trip.TripService
import com.dubswcraft.tripservicekata.user.User
import com.github.approval.Approvals
import org.scalatest.FlatSpec

class TripService_GoldenMaster extends FlatSpec {

  "The Golden master" should "be our safety net when making changes" in {

    val PAUL = User("PAUL")
    val JOE = User("JOE")
    val DEBORAH = User("DEBORAH")
    val SARAH = User("SARAH")
    val LOGGED_USER = User("LOGGED_USER")

    val tripService: TripService = new TripService()
    PAUL.addFriend(LOGGED_USER)
    JOE.addFriend(LOGGED_USER)
    SARAH.addFriend(LOGGED_USER)

    val tripsForJoe = tripService.getTripsByUser(JOE)
    val tripsForPaul = tripService.getTripsByUser(PAUL)
    val tripsForDeborah = tripService.getTripsByUser(DEBORAH)
    val tripsForSarah = tripService.getTripsByUser(SARAH)

    Approvals.verify(tripsForJoe + tripsForPaul + tripsForDeborah + tripsForSarah, Paths.get("src", "resources", "approval", "result.txt"))

  }

}
