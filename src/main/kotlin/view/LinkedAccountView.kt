package view

import controller.LinkedAccountController
import controller.ProfileController
import model.LinkedAccount
import utility.Helper
import java.util.ArrayList

class LinkedAccountView {


    fun viewBankAccounts(accounts: ArrayList<LinkedAccount>) {
        if(accounts.size == 0){
            println("\n-------- NO ACCOUNTS LINKED --------\n")
        }else{
            println("S.NO\t\tAccount Number\t\t\tBankName")
            for(i in 0 until accounts.size){
                println("${i+1}. \t\t${accounts[i].accountNumber}\t\t\t\t${accounts[i].bankName}")
            }
        }
    }

    fun accountOperations(profileController: ProfileController) {
        var isValidOption = false
        while(!isValidOption){
            for(accountOptions: enums.LinkedAccount in enums.LinkedAccount.values()){
                println("${accountOptions.ordinal+1}. $accountOptions")
            }
            print("Enter your choice: ")
            try{
                val option = readLine()!!.toInt()
                val helper = Helper()
                if(helper.checkValidRecord(option, enums.LinkedAccount.values().size)){
                    val entry: enums.LinkedAccount = enums.LinkedAccount.values()[option-1]
                    isValidOption = operations(entry, profileController)
                }else{
                    println("Enter proper input.")
                }
            }catch (error: Exception){
                println("Enter a valid option. Linked account view")
            }

        }
    }

    private fun operations(entry: enums.LinkedAccount, profileController: ProfileController): Boolean {
        when(entry){
            enums.LinkedAccount.LINK_ACCOUNT -> {
                val linkedAccountController = LinkedAccountController()
                linkedAccountController.linkAccount(profileController)
                return false
            }
            enums.LinkedAccount.REMOVE_ACCOUNT -> {
                val linkedAccountController = LinkedAccountController()
                viewBankAccounts(profileController.user?.accounts!!)
                if(profileController.user?.accounts!!.size > 0){
                    linkedAccountController.removeLinkedAccount(profileController)
                }
                return false
            }
            enums.LinkedAccount.BACK -> {
                return true
            }
        }
    }

    fun getAccountToBeDeleted(): Int {
        while(true){
            print("Select the account to be deleted: ")
            try{
                return readLine()!!.toInt()
            }catch (error: Exception){
                println("Invalid option. (Account to be deleted)")
            }
        }
    }

    fun removeStatus(status: Boolean) {
        if(status){
            println("Account removed Successfully")
        }else{
            println("Select from the linked accounts")
        }
    }

    fun getAccountNumber(): Int {
        print("Enter Account Number: ")
        return readLine()!!.toInt()
    }

    fun getBankName(): String {
        print("Enter Bank Name: ")
        return readLine()!!
    }

    fun linkStatus(status: Boolean) {
        if(status){
            println("Account linked Successfully")
        }else{
            println("Account already linked")
        }
    }

}
