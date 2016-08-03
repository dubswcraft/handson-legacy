package org.craftedsw.tripservicekata.user

import com.dubswcraft.tripservicekata.exception.CollaboratorCallException
import com.dubswcraft.tripservicekata.user.User

object UserSession {

	def getLoggedUser(): User = {
		throw new CollaboratorCallException(
			"UserSession.getLoggedUser() should not be called in an unit test");
	}

}
