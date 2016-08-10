package com.dubswcraft.tripservicekata.trip

import com.dubswcraft.tripservicekata.user.User

object TripDAO {

  val LONDON: Trip = new Trip("LONDON")
  val MILAN: Trip = new Trip("MILAN")
  val NEW_YORK: Trip = new Trip("NEW_YORK")
  val SINGAPORE: Trip = new Trip("SINGAPORE")
  val TOKYO: Trip = new Trip("TOKYO")
  val SYDNEY: Trip = new Trip("SYDNEY")
  private val tripsForPaul = List(LONDON, MILAN, SYDNEY)
  private val tripsForSarah = List(MILAN, NEW_YORK, SINGAPORE, TOKYO)
  private val tripsForJoe  = List(MILAN)
  private val tripsForDeborah = List(LONDON, NEW_YORK, SINGAPORE, TOKYO, SYDNEY)
  private val userDB = Map(("JOE", tripsForJoe), ("SARAH", tripsForSarah), ("PAUL", tripsForPaul), ("DEBORAH", tripsForDeborah))

  def findTripsByUser(user: User): List[Trip] = userDB(user.name)

}
