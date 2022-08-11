package controller

import model.User
import utility.ExpenseData
import utility.IncomingData
import utility.UserData
class ProfileController {

    var user: User? = null

    fun getUserDetails(input: String){
        val userData = UserData()
        user = userData.getProfileDetails(input)
    }

    fun getTotalExpense(userName: String): Double {
        val expenseData = ExpenseData()
        return expenseData.getTotalExpense(userName)
    }

    fun getTotalIncoming(userName: String): Double {
        val incomingData = IncomingData()
        return incomingData.getTotalIncoming(userName)
    }

}