package com.example.foregroundservice

import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.view.iterator
import com.example.foregroundservice.Common.Companion.toSet


class MainActivity : AppCompatActivity() {
    private lateinit var textBox: TextView
    private lateinit var setBox: EditText
    private lateinit var viewg: ViewGroup
    private lateinit var view: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (toSet) {
            setBox = findViewById(R.id.msg_set)
            setBox.setTextColor(Color.WHITE)
            setBox.setText("Type message here")

            setBox.setSelection(setBox.text.length)
            textBox = findViewById(R.id.msg_display)
            textBox.setText("")

            setBox.setBackgroundColor(Color.TRANSPARENT)
            textBox.setBackgroundColor(Color.TRANSPARENT)

            setBox?.setOnClickListener(
                object : DoubleClickListener() {
                    override fun onDoubleClick(v: View?) {
                        // Toast.makeText(applicationContext, "Double Click", Toast.LENGTH_SHORT).show()
                        val textToRepeat: String = setBox.text.toString() + "\t"
                        val repText: String = textToRepeat.repeat(100)

                        textBox.setText(repText)
                        textBox.setTextColor(R.color.transColorBlue)
                        textBox.alpha = 0.1f

                        setBox.isVisible = false
                        setBox.setText("")
                        
                        toSet = false

                    }
                })
            /*
            textBox?.setOnLongClickListener( object: View.OnLongClickListener {
                override fun onLongClick(v: View?): Boolean {
                    Toast.makeText(applicationContext, "Double Click", Toast.LENGTH_SHORT).show()
                    return true
                }
            })

             */
        }

    }

    abstract class DoubleClickListener : View.OnClickListener {
        var lastClickTime: Long = 0
        override fun onClick(v: View?) {
            val clickTime = System.currentTimeMillis()
            if (clickTime - lastClickTime < DOUBLE_CLICK_TIME_DELTA) {
                onDoubleClick(v)
            }
            lastClickTime = clickTime
        }

        abstract fun onDoubleClick(v: View?)

        companion object {
            private const val DOUBLE_CLICK_TIME_DELTA: Long = 300 //milliseconds
        }
    }
}
