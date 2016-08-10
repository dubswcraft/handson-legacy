package com.dubswcraft.tripservicekata.trip

import com.dubswcraft.tripservicekata.exception.UserNotLoggedInException
import com.dubswcraft.tripservicekata.user.User
import org.craftedsw.tripservicekata.user.UserSession

import scala.util.control.Breaks._

class TripService {

  def getTripsByUser(user: User): String = {
    var tripList: List[Trip] = List()
    val loggedInUser = UserSession getLoggedUser
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

      "Trps For User:" + user.name +
        "\n" +
        tripList.foldLeft("")((acc, trip) => acc + trip.name + "\t") +
        "\n\n"
    } else {
      throw new UserNotLoggedInException
    }
  }

}
