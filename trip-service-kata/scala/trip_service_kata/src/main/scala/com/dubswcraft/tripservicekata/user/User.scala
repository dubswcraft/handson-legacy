package com.dubswcraft.tripservicekata.user

import com.dubswcraft.tripservicekata.trip.Trip

case class User(name: String) {

	val tripList = scala.collection.mutable.ListBuffer.empty[Trip]
	val friendList = scala.collection.mutable.ListBuffer.empty[User]

	def friends(): List[User] = {
		friendList toList
	}

	def addFriend(user: User) = {
		friendList += user
	}

	def trips(): List[Trip] = {
		tripList toList
	}

	def addTrip(trip: Trip) = {
		tripList += trip
	}

}
