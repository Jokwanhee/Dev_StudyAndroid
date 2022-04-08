package com.example.gonistudy.Room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat

@Entity(tableName = "room_memo")
class RoomMemo{
    @PrimaryKey(autoGenerate = true) // no에 값이 없을 때 자동저장된 값을 db에 입력해준다.
    @ColumnInfo
    var no: Long? = null
    @ColumnInfo
    var content: String = ""
    @ColumnInfo(name = "date")
    var datetime: Long = 0

    constructor(content:String, datetime:Long){
        this.content = content
        this.datetime = datetime
    }

    fun simpleFormat(_date: Long): String {
        val simple = SimpleDateFormat("yyyy/MM/dd hh:mm")
        return simple.format(_date)
    }
}

