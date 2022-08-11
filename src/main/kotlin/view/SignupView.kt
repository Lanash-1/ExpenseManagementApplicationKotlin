package view

import controller.ProfileController
import utility.BankAccountData
import utility.Helper
import utility.UserData
import utility.Utility

class SignupView {

    val helper = Helper()
    val util = Utility()

    fun signup() {
        val email = getInput("email", "EmailID", "EmailID already Exists")
        if(email == ""){
            return
        }

        var password: String
        do {
            password = getInput("Enter password")
        }while (!helper.fieldValidation(password))

        verifyPassword(password)

        val userName = getInput("Username", "UserID", "UserName already taken")

        var firstName: String
        do{
            firstName = getInput("Enter first name: ")
        }while(!helper.fieldValidation(firstName))

        var lastName: String
        do{
            lastName = getInput("Enter last name: ")
        }while (!helper.fieldValidation(lastName))


        val userData = UserData()
        userData.createAccount(userName,firstName,lastName, email, password)
        println("----------------------------------------\nAccount created Successfully.\n----------------------------------------")
        val profileController = ProfileController()
        profileController.getUserDetails(email)
        val bankAccountData = BankAccountData()
        profileController.user?.accounts = bankAccountData.getBankDetails(profileController.user?.userName!!)
        val dashboardView = DashboardView()
        dashboardView.viewDashboard(profileController)
    }

    fun getInput(displayMessage: String): String{
        print(displayMessage)
        return readLine()!!
    }

    fun getInput(field: String, columnName: String, error: String): String{
        var data: String = ""
        while(true){
            if(field == "email"){
                data = getInput("Enter email id: ")
            }else{
                data = getInput("Create user Id: ")
            }

            if(helper.fieldValidation(data)){
                if(util.checkUnique(data, columnName)){
                    break;
                }else{
                    println(error)
                    return ""
                }
            }
        }
        return data
    }

    fun verifyPassword(password: String){
        while (true){
            val rePassword = getInput("Re-enter password: ")
            if(helper.validatePassword(password, rePassword)){
                break
            }else{
                println("Password not matching, Enter again")
            }
        }
    }
}