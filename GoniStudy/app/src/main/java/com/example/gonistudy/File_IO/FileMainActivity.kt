package com.example.gonistudy.File_IO

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gonistudy.databinding.ActivityFileMainBinding
import java.io.*
import java.lang.StringBuilder

// Device File Explorer / data / data / com.example.gonistudy
// 새로고침 우클릭 Synchronize

class FileMainActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityFileMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding){
            val directoryName = "memo"
            val fileName = "memo1.txt"
            assignButton.setOnClickListener {
                val content = writeTextView.text.toString()
                writeTextFile(directoryName, fileName, content)
            }
            val path = directoryName + "/" + fileName
            val writtenContent = readTextFile(path)
            readTextView.text = writtenContent

        }
    }

    fun readTextFile(path:String): String{
        val fullPath = File(filesDir.path + "/" + path)
        if (!fullPath.exists()) return ""

        val reader = FileReader(fullPath)
        val buffer = BufferedReader(reader)

        val result = StringBuilder() // StringBuffer()
        var temp:String? = ""
        while (true){
            temp = buffer.readLine() // readline() 줄바꿈 하지 않음
            if (temp == null) break
            result.append(temp).append("\n")
        }
        buffer.close()
        reader.close()
        return result.toString()
    }

    private fun writeTextFile(directory:String, file:String, content:String) {
        // 앱 기본 경로/files/memo 경로
        val dir = File(filesDir.path + "/" + directory)
        if (!dir.exists()) dir.mkdirs()

        val fullPath = dir.path + "/" + file
        val writer = FileWriter(fullPath)
        val buffer = BufferedWriter(writer)
        buffer.write(content)
        buffer.close()
        writer.close()
    }
}