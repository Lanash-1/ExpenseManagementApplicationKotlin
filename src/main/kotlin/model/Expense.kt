package model

data class Expense(val amount: Double, val category: String, val expenseId: Int, val account: LinkedAccount)
