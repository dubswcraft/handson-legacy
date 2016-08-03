package com.dubswcraft.tripservicekata.trip

import com.dubswcraft.tripservicekata.exception.CollaboratorCallException
import com.dubswcraft.tripservicekata.user.User

object TripDAO {

	def findTripsByUser(user: User): List[Trip] = {
		throw new CollaboratorCallException(
			"TripDAO should not be invoked on an unit test.");
	}

}
