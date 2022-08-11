package utility

import interfaces.UserDataServices
import model.User
import java.sql.*
import java.util.*

class UserData(
    private var conn: Connection? = null,
    private var st: Statement? = null,
    private var rs: ResultSet? = null,
    private var query: String? = null,
    private val username: String = "root",
    private val password: String = "password",
    private val url: String = "jdbc:mysql://localhost:3306/ExpenseManagementApplication"
): UserDataServices {

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



    override fun getProfileDetails(input: String): User {
        query = "select * from UserData where EmailID = '"+input+"' or UserID = '"+input+"'";
        st = conn!!.createStatement()
        rs = st!!.executeQuery(query)
        while(rs!!.next()){
            return User(rs!!.getString(1), rs!!.getString(2), rs!!.getString(3), rs!!.getString(4), rs!!.getString(5))
        }
        return User("","","","","")
    }

    override fun createAccount(
        userName: String,
        firstName: String,
        lastName: String,
        emailId: String,
        password: String
    ) {
        query = "insert into UserData(UserID, FirstName, LastName, EmailID, Password) values('$userName','$firstName','$lastName','$emailId','$password')"
        st = conn!!.createStatement()
        st!!.executeUpdate(query)
    }

}