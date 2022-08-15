package utility

import interfaces.PrintService
import model.Expense
import model.Incoming
import java.io.File

class PrintData: PrintService {

    override fun printIncomingHistory(incoming: ArrayList<Incoming>) {
        val fileName = "incomingHistory.txt"
        val file = File(fileName)
        val text = "S.No\tAccountNumber\tAmount\n"
        var result: String = text
        for(i in 0 until incoming.size){
            result += "\n${i + 1}. \t${incoming[i].account.accountNumber}\t${incoming[i].amount}\n"
        }
        file.writeText(result)

        println("\nsuccessfully printed expense History.\n")
    }

    override fun printExpenseHistory(expense: ArrayList<Expense>) {
        val fileName = "expenseHistory.txt"
        val file = File(fileName)

        val text = "S.No\tAccount Number\tAmount\tCategory\n"
        var result: String = text
        for(i in 0 until expense.size){
            result += "\n${i+1}. \t${expense[i].account.accountNumber}\t${expense[i].amount}\t${expense[i].category}\n"
        }
        file.writeText(result)
        println("\nsuccessfully printed expense History.\n")
    }
}