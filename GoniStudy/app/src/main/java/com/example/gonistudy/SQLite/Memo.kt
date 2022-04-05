package com.example.gonistudy.SQLite

import java.text.SimpleDateFormat

data class Memo(
    val no: Long?,
    val content: String,
    val datetime: Long
){
    fun simpleFormat(item:Long):String{
        val simple = SimpleDateFormat("yyyy/MM/dd hh:mm")
        val result = simple.format(item)
        return result
    }
}
