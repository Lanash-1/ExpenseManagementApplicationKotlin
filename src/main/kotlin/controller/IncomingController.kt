package controller

import model.Incoming
import model.LinkedAccount
import utility.Helper
import utility.IncomingData
import view.*

class IncomingController{

    private val incomingHistoryView = IncomingHistoryView()
    private val incomingData = IncomingData()
    private val helper = Helper()
    private val incoming = Incoming(0.0, 0, LinkedAccount(0,""))
    private val addIncomingView = AddIncomingView()
    private val editIncomingView = EditIncomingView()

    fun addIncoming(profileController: ProfileController) {
        if(profileController.user?.accounts?.size!! > 0){
            val linkedAccountsView = LinkedAccountView()
            linkedAccountsView.viewBankAccounts(profileController.user?.accounts!!)
            val index = helper.getAccount()
            if(profileController.user?.accounts?.size!! >= index){
                incoming.account = profileController.user?.accounts!![index-1]
                incoming.amount = helper.getAmount()
                incomingData.addIncoming(incoming, profileController)
                addIncomingView.addIncomingStatus()
            }else{
                addIncomingView.noAccount()
            }
        }else{
            addIncomingView.showAddAccount()
        }
    }

    fun viewIncomingHistory(profileController: ProfileController) {
        var choice = 0
        while(choice != 5){
            choice = incomingHistoryView.viewIncoming(incomingData.getIncomingHistory(profileController.user?.userName!!), profileController)
        }
    }


    fun editIncoming(incomingList: ArrayList<Incoming>, profileController: ProfileController){
        while(true){
            incomingHistoryView.displayIncoming(incomingList)
            val recordToBeEdited: Int = helper.getRecord()
            if(helper.checkValidRecord(recordToBeEdited, incomingList.size)){
                var amount = 0.0
                while(amount <= 0){
                    amount = helper.getAmount()
                }
                val linkedAccountView = LinkedAccountView()
                linkedAccountView.viewBankAccounts(profileController.user?.accounts!!)
                var index: Int
                do index = helper.getAccount()
                while (!helper.checkValidRecord(index, incomingList.size))
                val account = LinkedAccount(0,"")
                account.accountNumber = profileController.user?.accounts!![index-1].accountNumber
                account.bankName = profileController.user?.accounts!![index-1].bankName
                incoming.account = account
                incoming.amount = amount
                incoming.incomingId = incomingList[recordToBeEdited-1].incomingId
                incomingData.editIncomingRecord(incoming)
                editIncomingView.displayStatus()
                break
            }else{
                editIncomingView.displayError()
            }
        }
    }


    fun deleteIncoming(incomingList: ArrayList<Incoming>){
        val deleteIncomingView = DeleteIncomingView()
        while(true){
            incomingHistoryView.displayIncoming(incomingList)
            val recordToBeDeleted: Int = helper.getRecord()
            if(helper.checkValidRecord(recordToBeDeleted, incomingList.size)){
                incomingData.deleteIncomingRecord(incomingList[recordToBeDeleted-1].incomingId)
                deleteIncomingView.deleteStatus()
                break
            }else{
                deleteIncomingView.displayError()
            }
        }
    }



}