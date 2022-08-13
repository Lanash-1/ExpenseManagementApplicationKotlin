package utility

import interfaces.BankAccountServices
import model.LinkedAccount
import java.sql.*
import java.util.*
import kotlin.collections.ArrayList

class BankAccountData (
    private var conn: Connection? = null,
    private var st: Statement? = null,
    private var rs: ResultSet? = null,
    private var query: String? = null,
    private val username: String = "root",
    private val password: String = "password",
    private val url: String = "jdbc:mysql://localhost:3306/DemoBase"
): BankAccountServices {

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

    override fun getBankDetails(userId: String): ArrayList<LinkedAccount> {
        query = "select AccountNumber, BankName from BankAccount where UserID = '$userId'"
        st = conn!!.createStatement()
        rs = st!!.executeQuery(query)
        val accounts: ArrayList<LinkedAccount> = ArrayList()
        while(rs!!.next()){
            accounts.add(LinkedAccount(rs!!.getInt(1), rs!!.getString(2)))
        }
        return accounts
    }

    override fun linkAccount(userId: String, account: LinkedAccount) {
        query = "insert into BankAccount values('"+userId+"',"+account.accountNumber+",'"+account.bankName+"')"
        st = conn!!.createStatement()
        st!!.executeUpdate(query)
    }

    override fun removeAccount(account: LinkedAccount) {
        query = "delete from BankAccount where AccountNumber = "+account.accountNumber
        st = conn?.createStatement()
        st!!.executeUpdate(query)
        query = "delete from Incoming where AccountNumber = "+account.accountNumber
        st = conn?.createStatement()
        st!!.executeUpdate(query)
        query = "delete from Expense where AccountNumber = "+account.accountNumber
        st = conn?.createStatement()
        st!!.executeUpdate(query)
    }
}