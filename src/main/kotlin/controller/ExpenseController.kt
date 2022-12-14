package controller

import enums.Action
import model.Expense
import model.LinkedAccount
import utility.ExpenseData
import utility.Helper
import view.AddStatusView
import view.ExpenseHistoryView
import view.LinkedAccountView

class ExpenseController: AddStatusView() {

    private val expenseHistoryView = ExpenseHistoryView()
    private val expenseData = ExpenseData()
    private val helper = Helper()
    private val expense = Expense(0.0, "", 0, LinkedAccount(0,""))

    fun addExpense(profileController: ProfileController) {
        if(profileController.user?.accounts?.size!! > 0){
            val linkedAccountsView = LinkedAccountView()
            linkedAccountsView.viewBankAccounts(profileController.user?.accounts!!)
            val index = helper.getAccount()
            if(helper.checkValidRecord(index, profileController.user?.accounts?.size!!)){
                expense.account = profileController.user?.accounts!![index-1]
                expense.amount = helper.getAmount()
                expense.category = helper.getCategory()
                expenseData.addExpense(expense, profileController)
                addStatus(Action.EXPENSE)
            }else{
                noAccount()
            }
        }else{
            showAddAccount()
        }
    }

    fun viewExpenseHistory(profileController: ProfileController) {
        var choice = 0
        while(choice != 6){
            choice = expenseHistoryView.viewExpense(expenseData.getExpenseHistory(profileController.user?.userName!!), profileController)
        }
    }

    fun deleteExpense(expenseList: ArrayList<Expense>){
        while(true){
            expenseHistoryView.displayExpense(expenseList)
            val recordToBeDeleted: Int = helper.getRecord()
            if(helper.checkValidRecord(recordToBeDeleted, expenseList.size)){
                expenseData.deleteExpenseRecord(expenseList[recordToBeDeleted-1].expenseId)
                deleteStatus()
                break
            }else{
                displayError()
            }
        }
    }

    fun editExpense(expenseList: ArrayList<Expense>, profileController: ProfileController){
        while(true){
            expenseHistoryView.displayExpense(expenseList)
            val recordToBeEdited: Int = helper.getRecord()
            if(helper.checkValidRecord(recordToBeEdited, expenseList.size)){
                val amount = helper.getAmount()
                val linkedAccountView = LinkedAccountView()
                linkedAccountView.viewBankAccounts(profileController.user?.accounts!!)
                var index: Int
                do index = helper.getAccount()
                while (!helper.checkValidRecord(index, expenseList.size))
                val account = LinkedAccount(0,"")
                account.accountNumber = profileController.user?.accounts!![index-1].accountNumber
                account.bankName = profileController.user?.accounts!![index-1].bankName
                expense.account = account
                expense.amount = amount
                expense.category = helper.getCategory()
                expense.expenseId = expenseList[recordToBeEdited-1].expenseId
                expenseData.editExpenseRecord(expense)
                editStatus()
                break
            }else{
                displayError()
            }
        }
    }

}