package view

import controller.ProfileController
import utility.Utility

class LoginView {

    fun login() {
        val input: String = getEmailOrUserName()
        val password: String = getPassword()
        if(validation(input, password)){
            println("validated")
            val profileController = ProfileController()
            profileController.getUserDetails(input)
        }
    }

    private fun validation(input: String, password: String): Boolean {
        val util = Utility()
        return if(util.loginValidation(input, password)){
            println("Logged in successfully")
            true
        }else{
            println("Invalid login credentials.")
            false
        }
    }

    private fun getPassword(): String {
        print("Enter password: ")
        return readLine()!!
    }

    private fun getEmailOrUserName(): String {
        print("Enter emailID / UserName: ")
        return readLine()!!
    }

}