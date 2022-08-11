package utility

import controller.ProfileController
import interfaces.ExpenseServices
import model.Expense
import model.LinkedAccount
import java.sql.*
import java.util.*
import kotlin.collections.ArrayList

class ExpenseData(
    private var conn: Connection? = null,
    private var st: Statement? = null,
    private var rs: ResultSet? = null,
    private var query: String? = null,
    private val username: String = "root",
    private val password: String = "password",
    private val url: String = "jdbc:mysql://localhost:3306/ExpenseManagementApplication"
): ExpenseServices {

    private fun getConnection() {
        val connectionProps = Properties()
        connectionProps["user"] = username
        connectionProps["password"] = password
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance()
            conn = DriverManager.getConnection(url, connectionProps)
        } catch (ex: SQLException) {
            ex.printStackTrace()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    init{
        getConnection()
    }

    override fun addExpense(expense: Expense, profileController: ProfileController) {
        query = "insert into Expense(UserID, Category, Amount, AccountNumber) values('"+profileController.user.userName+"','"+expense.category+"',"+expense.amount+","+expense.account.accountNumber+")"
        st = conn!!.createStatement()
        st!!.executeUpdate(query)
    }

    override fun getExpenseHistory(userId: String): ArrayList<Expense> {
        query = "select * from Expense where UserID = '$userId'"
        st = conn!!.createStatement()
        rs = st!!.executeQuery(query)
        var expenseHistory: ArrayList<Expense> = ArrayList()
        while(rs!!.next()){
            expenseHistory.add(Expense(rs!!.getDouble(3), rs!!.getString(2), rs!!.getInt(5), LinkedAccount(rs!!.getInt(4), "")))
        }
        return expenseHistory
    }

    override fun getTotalExpense(userId: String): Double {
        query = "select sum(Amount) from Expense where UserID = '$userId'";
        st = conn!!.createStatement();
        rs = st!!.executeQuery(query);
        while(rs!!.next()){
            return rs!!.getDouble(1);
        }
        return 0.0;
    }

    override fun deleteExpenseRecord(expenseId: Int) {
        query = "delete from Expense where ExpenseID = $expenseId"
        st = conn!!.createStatement()
        st!!.executeUpdate(query)
    }

    override fun editExpenseRecord(expense: Expense) {
        query = "update Expense set Amount = "+expense.amount+", AccountNumber = "+expense.account.accountNumber+", Category = '"+expense.category+"' where ExpenseID = "+expense.expenseId;
        st = conn!!.createStatement();
        st!!.executeUpdate(query);
    }
}