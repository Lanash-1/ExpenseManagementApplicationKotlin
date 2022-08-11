package utility

class Helper {

    fun checkValidRecord(record: Int, size: Int): Boolean{
        return size >= record && record != 0
    }
}