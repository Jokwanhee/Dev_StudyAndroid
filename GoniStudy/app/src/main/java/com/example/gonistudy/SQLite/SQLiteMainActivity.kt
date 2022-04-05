package com.example.gonistudy.SQLite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gonistudy.R
import com.example.gonistudy.databinding.ActivitySqliteMainBinding

class SQLiteMainActivity : AppCompatActivity() {
    val binding by lazy {
        ActivitySqliteMainBinding.inflate(layoutInflater)
    }

    val DB_NAME = "sqlite.sql"
    val DB_VERSION = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val helper = SqliteHelper(this, DB_NAME, DB_VERSION)
        val memos = helper.selectMemo()

        val sqlRecyclerAdapter = SqliteRecyclerAdapter(memos)
        with(binding){
            recyclerView.run {
                adapter = sqlRecyclerAdapter
                layoutManager = LinearLayoutManager(this@SQLiteMainActivity)
            }

            sqlButton.setOnClickListener {
                val content = sqlEditText.text.toString()
                if (content.isNotEmpty()){
                    val memo = Memo(null, content, System.currentTimeMillis())
                    helper.insertMemo(memo)

                    // 기존 작성글 삭제
                    sqlEditText.setText("")
                    // 목록 갱신
                    val initItems = helper.selectMemo()
                    val sqlRecyclerAdapter = SqliteRecyclerAdapter(initItems)
                    recyclerView.adapter = sqlRecyclerAdapter
                    recyclerView.layoutManager = LinearLayoutManager(this@SQLiteMainActivity)
                    recyclerView.adapter?.notifyDataSetChanged()
                }
            }
        }

    }
}