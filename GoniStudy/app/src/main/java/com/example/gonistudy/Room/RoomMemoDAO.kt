package com.example.gonistudy.Room

import androidx.room.*

@Dao
interface RoomMemoDAO {

    @Query("select * from room_memo")
    fun getAll():List<RoomMemo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomMemo: RoomMemo)

    @Delete
    fun delete(roomMemo: RoomMemo)
}