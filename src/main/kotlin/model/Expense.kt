package model

data class Expense(var amount: Double, var category: String, var expenseId: Int, var account: LinkedAccount)
