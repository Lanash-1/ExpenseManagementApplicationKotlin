package utility

import interfaces.BankAccountServices
import model.LinkedAccount
import java.sql.*
import java.util.*
import kotlin.collections.ArrayList

class BankAccountData(
    private var conn: Connection? = null,
    private var st: Statement? = null,
    private var rs: ResultSet? = null,
    private var query: String? = null,
    private val username: String = "root",
    private val password: String = "password",
    private val url: String = "jdbc:mysql://localhost:3306/ExpenseManagementApplication"
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
        TODO("Not yet implemented")
    }

    override fun linkAccount(userId: String, accounts: LinkedAccount) {
        TODO("Not yet implemented")
    }

    override fun removeAccount(accounts: LinkedAccount) {
        TODO("Not yet implemented")
    }
}