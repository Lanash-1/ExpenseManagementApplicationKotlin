package view

class AddExpenseView {
    fun addExpenseStatus(){
        println("\n---------- Expense added to your profile ----------\n")
    }

    fun showAddAccount(){
        println("No accounts linked. Link a account to add Expense(expense view)")
    }

    fun noAccount(){
        println("Select from available accounts")
    }
}