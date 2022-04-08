package com.example.gonistudy.Room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.gonistudy.R
import com.example.gonistudy.databinding.ActivityRoomMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RoomMainActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityRoomMainBinding.inflate(layoutInflater)
    }
    lateinit var helper: RoomHelper
    lateinit var roomAdapter: RoomRecyclerViewAdapter

    val memoList = mutableListOf<RoomMemo>()

    lateinit var memoDAO: RoomMemoDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        helper = Room.databaseBuilder(this, RoomHelper::class.java, "room_db")
//            .allowMainThreadQueries() // 공부할 때 만 쓴다. (룸은 메인쓰레드 허용 x, 하지만 공부하기위해 허용가능한 메소드사용)
            .build()

        memoDAO = helper.roomMemoDao()
        roomAdapter = RoomRecyclerViewAdapter(memoList)

        refreshAdapter()

        with(binding) {
            recyclerView.adapter = roomAdapter
            recyclerView.layoutManager = LinearLayoutManager(this@RoomMainActivity)

            roomButton.setOnClickListener {
                val content = roomEditText.text.toString()
                if (content.isNotEmpty()) {
                    val datetime = System.currentTimeMillis()
                    val memo = RoomMemo(content, datetime)
                    roomEditText.setText("")
                    insertMemo(memo)
                }
            }
        }
    }

    fun insertMemo(memo:RoomMemo){
        CoroutineScope(Dispatchers.IO).launch {
            memoDAO.insert(memo)
            refreshAdapter()
        }

    }

    fun refreshAdapter() {
        CoroutineScope(Dispatchers.IO).launch {
            memoList.clear()
            memoList.addAll(memoDAO.getAll())
            withContext(Dispatchers.Main) {
                roomAdapter.notifyDataSetChanged()
            }
        }

    }
}