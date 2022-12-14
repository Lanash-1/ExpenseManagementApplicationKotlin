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
            println("\nS.NO\t\tAccount Number\t\t\tBankName")
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
                    println("\nEnter proper input.\n")
                }
            }catch (error: Exception){
                println("\nEnter a valid option. Linked account view\n")
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
                println("\nInvalid option.\n")
            }
        }
    }

    fun removeStatus(status: Boolean) {
        if(status){
            println("\nAccount removed Successfully\n")
        }else{
            println("\nSelect from the linked accounts\n")
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
            println("\nAccount linked Successfully\n")
        }else{
            println("\nAccount already linked\n")
        }
    }

}
