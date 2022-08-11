package controller

import model.LinkedAccount
import utility.BankAccountData
import utility.Utility
import view.LinkedAccountView

class LinkedAccountController {

    val bankAccountData: BankAccountData
    val linkedAccountView: LinkedAccountView
    init {
        bankAccountData = BankAccountData()
        linkedAccountView = LinkedAccountView()
    }

    fun getAccountDetails(profileController: ProfileController) {
        profileController.user?.accounts = bankAccountData.getBankDetails(profileController.user?.userName!!)
        linkedAccountView.viewBankAccounts(profileController.user?.accounts!!)
        linkedAccountView.accountOperations(profileController)
    }

    fun removeLinkedAccount(profileController: ProfileController){
        val index: Int = linkedAccountView.getAccountToBeDeleted()
        if(profileController.user?.accounts!!.size >= index-1){
            bankAccountData.removeAccount(profileController.user?.accounts!![index-1])
            linkedAccountView.removeStatus(true)
            profileController.user?.accounts = bankAccountData.getBankDetails(profileController.user?.userName!!)
        }else{
            linkedAccountView.removeStatus(false)
        }
        linkedAccountView.viewBankAccounts(profileController.user?.accounts!!)
    }

    fun linkAccount(profileController: ProfileController){
        var account = LinkedAccount(linkedAccountView.getAccountNumber(),"")
        val util = Utility()

        if(util.linkedAccountExists(account.accountNumber)){
            account.bankName = linkedAccountView.getBankName()
            bankAccountData.linkAccount(profileController.user?.userName!!, account)
            linkedAccountView.linkStatus(true)
            profileController.user?.accounts = bankAccountData.getBankDetails(profileController.user?.userName!!)
        }else{
            linkedAccountView.linkStatus(false)
        }
        linkedAccountView.viewBankAccounts(profileController.user?.accounts!!)

    }
}