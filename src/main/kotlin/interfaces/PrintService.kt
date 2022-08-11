package interfaces

import model.Expense
import model.Incoming

interface PrintService {
    fun printIncomingHistory(incoming: ArrayList<Incoming>)
    fun printExpenseHistory(expense: ArrayList<Expense>)
}