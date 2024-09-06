package com.liverussia.cr

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class StrokedTextView @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyle: Int = 0) : AppCompatTextView(context!!, attrs, defStyle) {

    private var strokeLayout: StaticLayout? = null
    private var origLayout: StaticLayout? = null
    private val strokePaint = TextPaint()

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        if (changed) {
            strokePaint.set(paint)
            strokePaint.style = Paint.Style.STROKE
            strokePaint.strokeWidth = 4f
            strokePaint.color = Color.BLACK
            strokeLayout = StaticLayout.Builder.obtain(text.toString(), 0, text.length, strokePaint, width)
                .setIncludePad(includeFontPadding)
                .build()

            paint.color = currentTextColor
            origLayout = StaticLayout.Builder.obtain(text, 0, text.length, paint, width)
                .setIncludePad(includeFontPadding)
                .build()
        }
    }

    override fun onDraw(canvas: Canvas) {
        strokeLayout!!.draw(canvas)
        origLayout!!.draw(canvas)
    }
}
