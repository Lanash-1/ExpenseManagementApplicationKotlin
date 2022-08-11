package interfaces

import controller.ProfileController
import model.Incoming

interface IncomingServices {
    fun addIncoming(incoming: Incoming, profileController: ProfileController)
    fun getIncomingHistory(userName: String): ArrayList<Incoming>
    fun getTotalIncoming(userId: String): Double
    fun deleteIncomingRecord(incomingId: Int)
    fun editIncomingRecord(incoming: Incoming)
}