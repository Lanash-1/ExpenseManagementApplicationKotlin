package interfaces

import controller.ProfileController
import model.Expense

interface ExpenseServices {
    fun addExpense(expense: Expense, profileController: ProfileController)
    fun getExpenseHistory(userId: String): ArrayList<Expense>
    fun getTotalExpense(userId: String): Double
    fun deleteExpenseRecord(expenseId: Int)
    fun editExpenseRecord(expense: Expense)
}