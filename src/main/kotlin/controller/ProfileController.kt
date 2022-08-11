package controller

import model.User
import utility.UserData

class ProfileController {
    var user: User? = null

    fun getUserDetails(input: String){
        val userData = UserData()
        user = userData.getProfileDetails(input)
    }

}