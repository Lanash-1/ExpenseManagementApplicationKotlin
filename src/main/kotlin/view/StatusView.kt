package view

import enums.Action

abstract class StatusView {
    fun showAddAccount(){
        println("\nNo accounts linked. Link a account to continue\n")
    }

    fun noAccount(){
        println("\nSelect from available accounts\n")
    }
    abstract fun addStatus(action: Action)

    fun editStatus(){
        println("\nRecord edited successfully\n")
    }

    fun deleteStatus(){
        println("\nRecord deleted successfully\n")
    }

    fun displayError(){
        println("\nSelect from available record\n")
    }
}