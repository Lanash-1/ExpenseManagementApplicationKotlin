package view

import controller.ExpenseController
import controller.ProfileController
import model.Expense
import utility.ExpenseData
import utility.Helper
import utility.PrintData
import utility.Utility
import java.util.ArrayList

class ExpenseHistoryView {

    private val expenseData = ExpenseData()
    var expense = ArrayList<Expense>()
    private val helper = Helper()
    private val util = Utility()

    fun displayExpense(expenseList: ArrayList<Expense>) {
        if(expenseList.size == 0){
            println("No expense to show")
        }else{
            println("\nS.No\t\tAccount Number\t\tAmount\t\tCategory")
            for(i in 0 until expenseList.size){
                println("${i+1}.\t\t\t${expenseList[i].account.accountNumber}\t\t\t\t${expenseList[i].amount}\t\t\t${expenseList[i].category}")
            }
        }
    }

    fun viewExpense(expenseList: ArrayList<Expense>, profileController: ProfileController): Int {
        expense = expenseList
        if(expenseList.size == 0){
            println("\nNo expense to show")
        }else{
            while(true){
                displayExpense(expenseList)
                for(entry: enums.Expense in enums.Expense.values()){
                    println("${entry.ordinal+1}. $entry")
                }
                print("Enter your choice: ")
                try {
                    val option = readLine()!!.toInt()
                    if(helper.checkValidRecord(option, enums.Expense.values().size)){
                        val entry = enums.Expense.values()[option-1]
                        return expenseHistoryOperations(entry, profileController)
                    }else{
                        println("Enter proper input.")
                    }
                }catch (error: Exception){
                    println("Enter a valid option. (View expense history)")
                }
            }
        }
        return 6
    }

    private fun expenseHistoryOperations(entry: enums.Expense, profileController: ProfileController): Int {
        when(entry){
            enums.Expense.PRINT -> {
                val print = PrintData()
                print.printExpenseHistory(expense)
                return 0
            }
            enums.Expense.FILTER_BY_ACCOUNT -> {
                val linkedAccountView = LinkedAccountView()
                while(true){
                    linkedAccountView.viewBankAccounts(profileController.user?.accounts!!)
                    print("Select account to filter: ")
                    try {
                        val index = readLine()!!.toInt()
                        if(helper.checkValidRecord(index, profileController.user?.accounts!!.size)){
                            return viewExpense(util.filterExpense(profileController.user?.userName!!, profileController.user?.accounts!![index-1].accountNumber), profileController)
                        }
                    }catch (error: Exception){
                        println("Select valid account")
                    }
                }
            }
            enums.Expense.FILTER_BY_CATEGORY -> {
                while(true){
                    val categoryList: ArrayList<String> = util.getCategoryList(profileController.user?.userName!!)
                    for(i in 0 until categoryList.size){
                        println("${i+1}. ${categoryList[i]}")
                    }
                    print("Select category to filer: ")
                    try {
                        val category = readLine()!!.toInt()
                        if(helper.checkValidRecord(category, categoryList.size)){
                            return viewExpense(util.filterExpense(profileController.user?.userName!!, categoryList[category-1]), profileController)
                        }else{
                            println("Select from listed category")
                        }
                    }catch (error: Exception){
                        println("Enter valid option to filter")
                    }
                }
            }
            enums.Expense.EDIT -> {
                val expenseController = ExpenseController()
                expense = expenseData.getExpenseHistory(profileController.user?.userName!!)
                expenseController.editExpense(expense, profileController)
                return 4
            }
            enums.Expense.DELETE -> {
                val expenseController = ExpenseController()
                expense = expenseData.getExpenseHistory(profileController.user?.userName!!)
                expenseController.deleteExpense(expense)
                expense = expenseData.getExpenseHistory(profileController.user?.userName!!)
                return viewExpense(expenseData.getExpenseHistory(profileController.user?.userName!!),profileController)
            }
            enums.Expense.BACK -> return 6
        }
    }


}
