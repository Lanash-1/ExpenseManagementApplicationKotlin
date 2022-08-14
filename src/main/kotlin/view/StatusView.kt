package view

import enums.Action

abstract class StatusView {
    fun showAddAccount(){
        println("No accounts linked. Link a account to continue")
    }

    fun noAccount(){
        println("Select from available accounts")
    }
    abstract fun addStatus(action: Action)

    fun editStatus(){
        println("Record edited successfully")
    }

    fun deleteStatus(){
        println("Record deleted successfully")
    }

    fun displayError(){
        println("Select from available record")
    }
}