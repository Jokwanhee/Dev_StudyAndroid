package com.example.gonistudy.Widget

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SeekBar
import androidx.core.view.get
import androidx.core.widget.addTextChangedListener
import com.example.gonistudy.databinding.ActivityWidgetBinding
import kotlin.concurrent.thread

open class WidgetActivity : AppCompatActivity() {

    // view binding lazy 늦은 초기화 사용 (메모리 낭비를 줄이기 위해서)
    val binding by lazy {
        ActivityWidgetBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 학습한 위젯
        // 에딧택스트, 라디오 버튼, 체크박스, 토글, 스위치, 프로그래스바, 음량조절바, 별점 부여 바

        setEditText()
        setRadioButton()
        setCheckBox()
        setToggleAndSwitch()
        setProgressBar()
        setSeekBar()
        setRatingBar()

    }

    private fun setRatingBar() {
        with(binding){
            ratingBar.setOnRatingBarChangeListener { ratingBar, starsValue, isUsing ->
                if (isUsing){
                    if (starsValue > 0) textView.text = starsValue.toString()
                }
            }
        }
    }

    private fun setSeekBar() {
        with(binding){
            seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    if (p2) {
                        if (p1 > 0) textView.text = p1.toString()
                    }
                }
                override fun onStartTrackingTouch(p0: SeekBar?) {}
                override fun onStopTrackingTouch(p0: SeekBar?) {}
            })
        }
    }

    private fun setProgressBar() {
        // ------------메인스레드------------
        with(binding){
            showProgress(true)
            // ------------서브스레드------------
            // 서브스레드 내에서는 ui를 그려줄 수 없다. 메인에서만 처리
            thread(start=true){
                Thread.sleep(3000)
                runOnUiThread{
                    showProgress(false)
                }
            }
            // ------------서브스레드------------
        }
    }
    fun showProgress(show:Boolean){
        binding.progressLayout.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun setToggleAndSwitch() {
        with(binding){
            toggleButton.setOnCheckedChangeListener { toggle, isChecked ->
                Log.d("testing","toggle isChecked $isChecked")
            }
            switchButton.setOnCheckedChangeListener { switch, isChecked ->
                textView.text = if (isChecked) "Switch ON" else "Switch Off"
            }
        }
    }


    private fun setCheckBox() {
        with(binding){
            checkBox1.setOnCheckedChangeListener { checkBox, ischecked ->
                Log.d("testing","checkBox1 ${checkBox}")
                Log.d("testing","checkBox1 text ${checkBox.text}")
                Log.d("testing","ischecked1 ${ischecked}")
            }
            checkBox2.setOnCheckedChangeListener { checkBox, ischecked ->
                Log.d("testing","checkBox2 ${checkBox}")
                Log.d("testing","checkBox2 text ${checkBox.text}")
                Log.d("testing","ischecked2 ${ischecked}")
            }
            checkBox3.setOnCheckedChangeListener { checkBox, ischecked ->
                Log.d("testing","checkBox3 ${checkBox}")
                Log.d("testing","checkBox3 text ${checkBox.text}")
                Log.d("testing","ischecked3 ${ischecked}")
            }
        }
    }

    fun setEditText(){
        with(binding.editText){
            addTextChangedListener {
                binding.textView.text = it.toString()
            }
        }
    }

    fun setRadioButton(){
        with(binding.radioGroup){
            setOnCheckedChangeListener { radioGroup, i ->
                when (i){
                    1 -> binding.textView.text = radioGroup.get(0).id.toString()
                    2 -> binding.textView.text = radioGroup.get(1).id.toString()
                    3 -> binding.textView.text = radioGroup.get(2).id.toString()
                }
            }
        }
    }


}

