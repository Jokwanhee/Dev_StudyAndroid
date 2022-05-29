package com.goni99.musicplayer

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val permissions = Manifest.permission.READ_EXTERNAL_STORAGE
    val REQ_READ = 99

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (isPermitted()){
            startProcess()
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(permissions), REQ_READ)
        }
    }

    private fun startProcess() {
        val adapter = MusicAdapter()
        adapter.musicList.addAll(getMusicList())
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)
    }

    fun getMusicList() : List<Music>{
        // ContentResolver
        // 1. 데이터 테이블 주소
        val musicListUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        // 2. 가져올 데이터 컬럼 정의
        val proj = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.DURATION
        )
        // 3. 컨텐트 리졸버에 해당 데이터 요청
        val cursor = contentResolver.query(musicListUri,proj,null,null,null)
        // 4. 커서로 전달받은 데이터를 꺼내서 저장
        val musicList = mutableListOf<Music>()
        while (cursor?.moveToNext() ?: false){
            val id = cursor?.getString(0)
            val title = cursor?.getString(1)
            val artist = cursor?.getString(2)
            val albumId = cursor?.getString(3)
            val duration = cursor?.getLong(4)

            val music = Music(id!!, title, artist, albumId, duration)
            musicList.add(music)
        }
        return musicList
    }

    fun isPermitted():Boolean{
        if (ContextCompat.checkSelfPermission(this, permissions) != PackageManager.PERMISSION_GRANTED){
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode){
            REQ_READ -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    startProcess()
                } else {
                    finish()
                }
            }
        }
    }
}