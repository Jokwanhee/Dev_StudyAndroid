package com.example.gonistudy.CustomView

import android.content.Context
import android.graphics.*
import android.view.View

class CustomView(context: Context) : View(context) {

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val paint = Paint()
        paint.color = Color.BLACK
        paint.textSize = 100f

        canvas?.run {
            drawText("안녕하세요", 100f, 100f, paint)
            customDrawCircle(canvas)
            customDrawRectangle(canvas)
        }
    }

    fun customDrawCircle(canvas: Canvas) {
        val paint = Paint()
        paint.color = Color.BLUE
        paint.style = Paint.Style.FILL

        canvas.drawCircle(150f, 300f, 100f, paint)
    }

    fun customDrawRectangle(canvas: Canvas) {
        val paint = Paint()
        paint.color = Color.GREEN
        paint.strokeWidth = 20f
        paint.style = Paint.Style.STROKE

        val rect = Rect(50, 550, 250, 650)
        val rectf = RectF(50f, 450f, 250f, 650f)
        canvas.drawRect(rectf, paint)
    }

}