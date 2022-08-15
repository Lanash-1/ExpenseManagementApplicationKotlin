package view

import controller.*
import enums.Dashboard
import utility.Helper

class DashboardView {

    fun viewDashboard(profileController: ProfileController) {
        while(true){
            for (dashboardOption: Dashboard in Dashboard.values()){
                println("${dashboardOption.ordinal+1}. $dashboardOption")
            }
            print("Enter your choice: ")
            try{
                val entryOption = readLine()!!.toInt()
                val helper = Helper()
                if(helper.checkValidRecord(entryOption, Dashboard.values().size)){
                    val entry = Dashboard.values()[entryOption-1]
                    if(dashboardOperations(entry, profileController)){
                        break
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
                val expenseController = ExpenseController()
                expenseController.addExpense(profileController)
                return false
            }
            Dashboard.ADD_INCOMING -> {
                val incomingController = IncomingController()
                incomingController.addIncoming(profileController)
                return false
            }
            Dashboard.EXPENSE_HISTORY -> {
                val expenseController = ExpenseController()
                expenseController.viewExpenseHistory(profileController)
                return false
            }
            Dashboard.INCOMING_HISTORY -> {
                val incomingController = IncomingController()
                incomingController.viewIncomingHistory(profileController)
                return false
            }
            Dashboard.SIGN_OUT -> {
                return true
            }
        }
    }

}
