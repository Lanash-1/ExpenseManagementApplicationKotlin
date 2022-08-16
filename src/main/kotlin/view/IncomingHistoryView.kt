package view

import controller.IncomingController
import controller.ProfileController
import model.Incoming
import utility.Helper
import utility.IncomingData
import utility.PrintData
import utility.Utility

class IncomingHistoryView {

    private val incomingData = IncomingData()
    var incoming = ArrayList<Incoming>()
    private val helper = Helper()
    val util = Utility()

    fun displayIncoming(incomingList: ArrayList<Incoming>) {
        if(incomingList.size == 0){
            println("No incoming to show")
        }else{
            println("\nS.No\t\tAccount Number\t\t\tAmount")
            for(i in 0 until incomingList.size){
                println("${i+1}.\t\t\t${incomingList[i].account.accountNumber}\t\t\t\t${incomingList[i].amount}")
            }
        }
    }

    fun viewIncoming(incomingList: ArrayList<Incoming>, profileController: ProfileController): Int {
        incoming = incomingList
        if(incomingList.size == 0){
            println("\nNo incoming to show")
        }else{
            while(true){
                displayIncoming(incomingList)
                for(entry: enums.Incoming in enums.Incoming.values()){
                    println("${entry.ordinal+1}. $entry")
                }
                print("Enter your choice: ")
                try {
                    val option = readLine()!!.toInt()
                    if(helper.checkValidRecord(option, enums.Incoming.values().size)){
                        val entry = enums.Incoming.values()[option-1]
                        return incomingHistoryOperations(entry, profileController)
                    }else{
                        println("Enter proper input.")
                    }
                }catch (error: Exception){
                    println("Enter a valid option. (View incoming history)")
                }
            }
        }
        return 5
    }

    private fun incomingHistoryOperations(entry: enums.Incoming, profileController: ProfileController): Int {
        when(entry){
            enums.Incoming.PRINT -> {
                val print = PrintData()
                print.printIncomingHistory(incoming)
                return 0
            }
            enums.Incoming.FILTER -> {
                val linkedAccountView = LinkedAccountView()
                while (true) {
                    linkedAccountView.viewBankAccounts(profileController.user?.accounts!!)
                    print("Select account to filter: ")
                    try {
                        val index = readLine()!!.toInt()
                        if (helper.checkValidRecord(index, profileController.user?.accounts!!.size)) {
                            return viewIncoming(
                                util.filterIncoming(
                                    profileController.user?.userName!!,
                                    profileController.user?.accounts!![index - 1].accountNumber
                                ), profileController
                            )
                        }
                    } catch (error: Exception) {
                        println("\nSelect valid account")
                    }
                }
            }
            enums.Incoming.EDIT -> {
                val incomingController = IncomingController()
                incoming = incomingData.getIncomingHistory(profileController.user?.userName!!)
                incomingController.editIncoming(incoming, profileController)
                return 3
            }
            enums.Incoming.DELETE -> {
                val incomingController = IncomingController()
                incoming = incomingData.getIncomingHistory(profileController.user?.userName!!)
                incomingController.deleteIncoming(incoming)
                incoming = incomingData.getIncomingHistory(profileController.user?.userName!!)
                if(incoming.size == 0){
                    displayIncoming(incoming)
                    return 6
                }
                return viewIncoming(incomingData.getIncomingHistory(profileController.user?.userName!!), profileController)
            }
            enums.Incoming.BACK -> return 5
        }
    }


}
