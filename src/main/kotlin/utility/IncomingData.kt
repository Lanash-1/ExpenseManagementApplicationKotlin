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
    private val url: String = "jdbc:mysql://localhost:3306/DemoBase"
): IncomingServices{

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

    override fun addIncoming(incoming: Incoming, profileController: ProfileController) {
        query = "insert into Incoming(UserId, Amount, AccountNumber) values('"+profileController.user?.userName+"',"+incoming.amount+","+incoming.account.accountNumber+")"
        st = conn!!.createStatement()
        st!!.executeUpdate(query)
    }

    override fun getIncomingHistory(userName: String): ArrayList<Incoming> {
        query = "select * from Incoming where UserID = '$userName'"
        st = conn!!.createStatement()
        rs = st!!.executeQuery(query)
        val incomingHistory = ArrayList<Incoming>()
        while(rs!!.next()){
            val linkedAccount = LinkedAccount(rs!!.getInt(3),"")
            val incoming = Incoming(rs!!.getDouble(2),rs!!.getInt(4),linkedAccount)
            incomingHistory.add(incoming)
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