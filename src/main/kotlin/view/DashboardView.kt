package view

import controller.LinkedAccountController
import controller.ProfileController
import enums.Dashboard
import enums.Entry
import utility.Helper

class DashboardView {


    fun viewDashboard(profileController: ProfileController) {
        while(true){
            for (dashboardOption: Dashboard in Dashboard.values()){
                println("${dashboardOption.ordinal+1}. $dashboardOption")
            }
            print("Enter your choice: ")
            try{
                val option: String = readLine()!!
                val entryOption: Int = option.toInt()
                val helper = Helper()
                if(helper.checkValidRecord(entryOption, Dashboard.values().size)){
                    val entry: Dashboard = Dashboard.values()[entryOption-1]
                    if(dashboardOperations(entry, profileController)){
                        break;
                    }
                }else{
                    println("Enter proper input.")
                }
            }catch(error: Exception){
                println("Enter a valid option. (View dashboard)")
            }
        }

    }

    private fun dashboardOperations(entry: Dashboard, profileController: ProfileController): Boolean {
        when (entry){
            Dashboard.PROFILE -> {
                val profileView = ProfileView()
                profileView.getProfileDetails(profileController)
                return false
            }
            Dashboard.LINKED_ACCOUNTS -> {
                val linkedAccountController = LinkedAccountController()
                linkedAccountController.getAccountDetails(profileController)
                return false

            }
            Dashboard.ADD_EXPENSE -> {
                return false

            }
            Dashboard.ADD_INCOMING -> {
                return false

            }
            Dashboard.EXPENSE_HISTORY -> {
                return false

            }
            Dashboard.INCOMING_HISTORY -> {
                return false

            }
            Dashboard.SIGN_OUT -> {
                return true
            }
        }
        return false
    }

}
