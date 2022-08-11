package view

import controller.ProfileController

class ProfileView {
    fun getProfileDetails(profileController: ProfileController) {
        while(true){
            printProfileDetails(profileController)
            println("Press 0 to go back to dashboard")
            try {
                val input = readLine()!!.toInt()
                if (input != 0){
                    println("Enter a valid option")
                }else{
                    break
                }
            }catch (error: Exception){
                println("Enter a valid option")
            }
        }
    }

    private fun printProfileDetails(profileController: ProfileController) {
        println("\n-----------------  PROFILE  -----------------\n")
        println("UserName: " + profileController.user?.userName)
        println("FirstName: "+profileController.user?.firstName+"\t||\tLastName: "+profileController.user?.lastName)
        println("Email ID: "+ profileController.user?.firstName)
        println("Password: "+ profileController.user?.password)
        val expense: Double = profileController.getTotalExpense(profileController.user?.userName!!)
        val incoming: Double = profileController.getTotalIncoming(profileController.user?.userName!!)
        println("Total Incoming: $incoming\t\t\tTotal Expense: $expense")
        if(incoming-expense > 0){
            println("Your expenses are lower than your incomings by Rs." + (incoming-expense))
        }else if(incoming-expense < 0){
            println("Your expenses are higher than your incomings by Rs. " + (expense-incoming))
        }else{
            println("Your expenses and incomings are balanced")
        }
        println("\n---------------------------------------------\n")
    }

}
