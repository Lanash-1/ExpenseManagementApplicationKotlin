package utility

import model.Expense
import model.Incoming
import model.LinkedAccount
import java.sql.*
import java.util.*

class Utility(
    private var conn: Connection? = null,
    private var st: Statement? = null,
    private var rs: ResultSet? = null,
    private var query: String? = null,
    private val username: String = "root",
    private val password: String = "password",
    private val url: String = "jdbc:mysql://localhost:3306/DemoBase"
) {

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

    fun loginValidation(input: String, password: String): Boolean {
        query = "select count(*) from UserData where EmailID = '$input' and Password = '$password' or UserID = '$input' and Password = '$password';"
        try {
            st = conn!!.createStatement()
            rs = st!!.executeQuery (query)
            while(rs!!.next()){
                return rs!!.getInt(1) == 1
            }
        }catch (error: Exception){
            println("Error while login validation")
        }
        return false
    }

    fun checkUnique(input: String, field: String): Boolean{
        query = "select count($field), $field from UserData where $field = '$input'"
        st = conn!!.createStatement()
        rs = st!!.executeQuery (query)
        while(rs!!.next()){
            return rs!!.getInt(1) == 0
        }
        return true
    }

    fun linkedAccountExists(accountNumber: Int): Boolean{
        query = "select count(AccountNumber), AccountNumber from BankAccount where AccountNumber = $accountNumber"
        st = conn!!.createStatement()
        rs = st!!.executeQuery(query)
        while(rs!!.next()){
            return rs!!.getInt(1) == 0
        }
        return false
    }

    fun filterIncoming(userName: String, accountNumber: Int): ArrayList<Incoming> {
        query = "select * from Incoming where UserID = '$userName' and AccountNumber = $accountNumber"
        st = conn?.createStatement()
        rs = st?.executeQuery(query)
        val incomingHistory = ArrayList<Incoming>()
        while(rs!!.next()){
            val account = LinkedAccount(rs!!.getInt(3), "")
            val incoming = Incoming(rs!!.getDouble(2), 0, account)
            incomingHistory.add(incoming)
        }
        return incomingHistory

    }

    fun filterExpense(userName: String, accountNumber: Int): ArrayList<Expense> {
        query = "select * from Expense where UserID = '$userName' and AccountNumber = $accountNumber"
        st = conn?.createStatement()
        rs = st?.executeQuery(query)
        val expenseHistory = ArrayList<Expense>()
        while(rs!!.next()){
            val account = LinkedAccount(rs!!.getInt(4), "")
            val expense = Expense(rs!!.getDouble(3), rs!!.getString(2),0, account)
            expenseHistory.add(expense)
        }
        return expenseHistory
    }


    fun filterExpense(userName: String, category: String): ArrayList<Expense> {
        query = "select * from Expense where UserID = '$userName' and Category = '$category'"
        st = conn?.createStatement()
        rs = st?.executeQuery(query)
        val expenseHistory = ArrayList<Expense>()
        while(rs!!.next()){
            val account = LinkedAccount(rs!!.getInt(4), "")
            val expense = Expense(rs!!.getDouble(3), rs!!.getString(2),0, account)
            expenseHistory.add(expense)
        }
        return expenseHistory
    }



    fun getCategoryList(userName: String): ArrayList<String> {
        query = "select distinct Category from Expense where UserID = '$userName'"
        st = conn?.createStatement()
        rs = st?.executeQuery(query)
        val categoryList = ArrayList<String>()
        while(rs!!.next()){
            categoryList.add(rs!!.getString(1))
        }
        return categoryList
    }


}