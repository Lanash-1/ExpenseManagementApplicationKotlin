package interfaces

import model.User

interface UserDataServices {
    fun getProfileDetails(input: String): User
    fun createAccount(userName: String, firstName: String, lastName: String, emailId: String, password: String)
}