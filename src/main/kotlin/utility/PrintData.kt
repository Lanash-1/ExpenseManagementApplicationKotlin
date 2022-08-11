package utility

import interfaces.PrintService
import model.Expense
import model.Incoming
import java.io.File

class PrintData: PrintService {

    override fun printIncomingHistory(incoming: ArrayList<Incoming>) {
        val fileName = "incomingHistory.txt"
        val file = File(fileName)

        val text = "S.No\tAccountNumber\tAmount"
        file.writeText(text)
        var result: String
        for(i in 0..incoming.size-1){
            result = "\n${i+1}. \t${incoming[i].account.accountNumber}\t${incoming[i].amount}"
            file.writeText(result)
        }
        println("successfully printed expense History.")
    }

    override fun printExpenseHistory(expense: ArrayList<Expense>) {
        val fileName = "expenseHistory.txt"
        val file = File(fileName)

        val text = "\nS.No\tAccount Number\tAmount\tCategory"
        file.writeText(text)
        var result: String
        for(i in 0..expense.size-1){
            result = "\n${i+1}. \t${expense[i].account.accountNumber}\t${expense[i].amount}\t${expense[i].category}"
            file.writeText(result)
        }
        println("successfully printed expense History.")
    }
}