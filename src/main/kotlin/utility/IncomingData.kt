package utility

import controller.ProfileController
import interfaces.IncomingServices
import model.Incoming
import model.LinkedAccount
import java.sql.*
import java.util.*
import kotlin.collections.ArrayList

class IncomingData(
    private var conn: Connection? = null,
    private var st: Statement? = null,
    private var rs: ResultSet? = null,
    private var query: String? = null,
    private val username: String = "root",
    private val password: String = "password",
    private val url: String = "jdbc:mysql://localhost:3306/ExpenseManagementApplication"
): IncomingServices{

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

    override fun addIncoming(incoming: Incoming, profileController: ProfileController) {
        query = "insert into Incoming(UserId, Amount, AccountNumber) values('"+profileController.user.userName+"',"+incoming.amount+","+incoming.account.accountNumber+")"
        st = conn!!.createStatement()
        st!!.executeUpdate(query)
    }

    override fun getIncomingHistory(userName: String): ArrayList<Incoming> {
        query = "select * from Incoming where UserID = '$userName'"
        st = conn!!.createStatement()
        rs = st!!.executeQuery(query)
        var incomingHistory: ArrayList<Incoming> = ArrayList()
        while(rs!!.next()){
            incomingHistory.add(Incoming(rs!!.getDouble(2), rs!!.getInt(4), LinkedAccount(rs!!.getInt(3), "")))
        }
        return incomingHistory
    }

    override fun getTotalIncoming(userId: String): Double {
        query = "select sum(Amount) from Incoming where UserID = '$userId'"
        st = conn!!.createStatement()
        rs = st!!.executeQuery(query)
        while(rs!!.next()){
            return rs!!.getDouble(1)
        }
        return 0.0
    }

    override fun deleteIncomingRecord(incomingId: Int) {
        query = "delete from Incoming where IncomingID = $incomingId"
        st = conn!!.createStatement()
        st!!.executeUpdate(query)
    }

    override fun editIncomingRecord(incoming: Incoming) {
        query = "update Incoming set Amount = "+incoming.amount+", AccountNumber = "+incoming.account.accountNumber+" where IncomingID = "+incoming.incomingId
        st = conn!!.createStatement()
        st!!.executeUpdate(query)
    }

}