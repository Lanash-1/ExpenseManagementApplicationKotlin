package utility

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement
import java.util.Properties

class Utility(
    private var conn: Connection? = null,
    private var st: Statement? = null,
    private var rs: ResultSet? = null,
    private var query: String? = null,
    private val username: String = "root",
    private val password: String = "password",
    private val url: String = "jdbc:mysql://localhost:3306/ExpenseManagementApplication"
) {

    private fun getConnection() {
        val connectionProps = Properties()
        connectionProps["user"] = username
        connectionProps["password"] = password
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance()
            conn = DriverManager.getConnection(url, connectionProps)
        } catch (ex: SQLException) {
            // handle any errors
            ex.printStackTrace()
        } catch (ex: Exception) {
            // handle any errors
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

}