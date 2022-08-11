package view

import enums.Entry
import utility.Helper

class EntryView {

    fun entryView(){
        while(true){
            for (entry: Entry in Entry.values()){
                println("${entry.ordinal+1}. $entry")
            }
            print("Enter your choice: ")
            try {
                val option = readLine()!!
                val entryOption = option.toInt()
                val helper = Helper()
                if(helper.checkValidRecord(entryOption, Entry.values().size)){
                    val entry: Entry = Entry.values()[entryOption-1]
                    if(entryOperations(entry)){
                        break
                    }
                }else{
                    println("Enter proper input.")
                }
            }catch(error: Exception){
                println("Enter a valid option. ")
            }
            break
        }
    }

    private fun entryOperations(entry: Entry): Boolean {
        when (entry) {
            Entry.LOGIN -> {
                println("Login")
                val loginView = LoginView()
                loginView.login()
                return false
            }
            Entry.SIGNUP -> {
                println("Signup")
                val signupView = SignupView()
                signupView.signup()
                return false
            }
            Entry.EXIT -> {
                println("CLOSED")
                return true
            }
            else -> {
                println("Enter from available options.")
                return false
            }
        }
    }

}