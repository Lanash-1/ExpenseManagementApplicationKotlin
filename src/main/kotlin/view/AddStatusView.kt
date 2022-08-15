package view

import enums.Action
open class AddStatusView: StatusView() {
    override fun addStatus(action: Action) {
        when(action){
            Action.EXPENSE -> println("\n---------- Expense added to your profile ----------\n")
            Action.INCOMING -> println("\n---------- Incoming added to your profile ----------\n")
        }
    }
}