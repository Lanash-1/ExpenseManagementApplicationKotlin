package interfaces

import model.LinkedAccount

interface BankAccountServices {
    fun getBankDetails(userId: String): ArrayList<LinkedAccount>
    fun linkAccount(userId: String, accounts: LinkedAccount)
    fun removeAccount(accounts: LinkedAccount)
}