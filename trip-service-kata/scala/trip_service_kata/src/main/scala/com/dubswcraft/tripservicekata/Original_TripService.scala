package com.dubswcraft.tripservicekata

import com.dubswcraft.tripservicekata.exception.UserNotLoggedInException
import com.dubswcraft.tripservicekata.trip.{TripDAO, Trip}
import com.dubswcraft.tripservicekata.user.User
import org.craftedsw.tripservicekata.user.UserSession

import scala.util.control.Breaks.{breakable, break}

class Original_TripService {

	def getTripsByUser(user: User): List[Trip] = {
		var tripList: List[Trip] = List()
		val loggedInUser = UserSession getLoggedUser()
		var isFriend = false
		if (loggedInUser != null) {
			breakable {
        for (friend <- user.friends()) {
          if (friend == loggedInUser) {
            isFriend = true
            break
          }
        }
      }
			if (isFriend) {
				tripList = TripDAO.findTripsByUser(user)
			}
			tripList
		} else {
			throw new UserNotLoggedInException
		}
	}

}
