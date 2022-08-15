package utility

class Helper {

    fun checkValidRecord(record: Int, size: Int): Boolean{
        return size >= record && record != 0
    }

    fun fieldValidation(input: String): Boolean{
        if(input == ""){
            println("Field should not be empty")
            return false
        }
        return true
    }

    fun validatePassword(password: String, rePassword: String): Boolean{
        return rePassword == password
    }

    fun getAccount(): Int{
        while(true){
            print("Select account: ")
            try{
                return readLine()!!.toInt()
            }catch (error: Exception){
                println("Select a valid account.")
            }
        }
    }

    fun getAmount(): Double{
        while(true){
            print("Enter Amount: ")
            try {
                val amount: Double = readLine()!!.toDouble()
                if(amount > 0){
                    return amount
                }else{
                    println("Amount should be greater than zero")
                }
            }catch (error: Exception){
                println("Enter a valid amount.")
            }
        }
    }

    fun getRecord(): Int{
        while(true){
            println("Select a record: ")
            try {
                return readLine()!!.toInt()
            }catch (error: Exception){
                println("Select a valid record")
            }
        }
    }

    fun getCategory(): String{
        val category = listOf("GADGET", "ENTERTAINMENT", "FOOD", "TRAVEL", "HEALTH", "CUSTOM")
        while(true){
            for(i in category.indices){
                println("${i+1}. ${category[i]}")
            }
            println("Enter your choice: ")
            try {
                val choice = readLine()!!.toInt()
                if(choice in 1..6){
                    if(choice == 6){
                        while(true){
                            print("Enter your category: ")
                            val input = readLine()!!
                            if(fieldValidation(input)){
                                return input
                            }
                        }
                    }else{
                        return category[choice-1]
                    }
                }else{
                    println("Select from the given category")
                }
            }catch (error: Exception){
                println("Enter valid category option.")
            }
        }
    }
}