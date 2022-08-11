package model

data class User(val userName: String, val firstName: String ,val lastName: String, val emailId: String, val password: String, var accounts: ArrayList<LinkedAccount>)
