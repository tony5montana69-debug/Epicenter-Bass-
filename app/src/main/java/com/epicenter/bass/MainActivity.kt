
package com.epicenter.bass

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.SeekBar

class MainActivity : Activity() {

    private val blue = Color.rgb(0, 160, 255)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = Color.BLACK
        window.navigationBarColor = Color.BLACK

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(28, 30, 28, 30)
            setBackgroundColor(Color.rgb(5, 9, 20))
        }

        val title = TextView(this).apply {
            text = "EPICENTER"
            textSize = 34f
            setTextColor(blue)
            typeface = Typeface.DEFAULT_BOLD
            gravity = Gravity.CENTER
        }

        val subtitle = TextView(this).apply {
            text = "B A S S   B O O S T"
            textSize = 15f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
        }

        root.addView(title)
        root.addView(subtitle)

        val bassTitle = TextView(this).apply {
            text = "POTENCIA DE BAJOS"
            textSize = 18f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
            setPadding(0, 50, 0, 10)
        }

        root.addView(bassTitle)

        val bass = SeekBar(this).apply {
            max = 100
            progress = 50
        }

        root.addView(bass)

        val value = TextView(this).apply {
            text = "50%"
            textSize = 25f
            setTextColor(blue)
            gravity = Gravity.CENTER
            setPadding(0, 15, 0, 35)
        }

        bass.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    value.text = "$progress%"
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            }
        )

        root.addView(value)

        val info = TextView(this).apply {
            text = "ECUALIZADOR"
            textSize = 20f
            setTextColor(blue)
            gravity = Gravity.CENTER
            setPadding(0, 15, 0, 15)
        }

        root.addView(info)

        listOf("SUBGRAVES", "GRAVES", "MEDIOS", "AGUDOS").forEach {
            val label = TextView(this).apply {
                text = it
                textSize = 14f
                setTextColor(Color.WHITE)
            }
            root.addView(label)

            root.addView(SeekBar(this).apply {
                max = 100
                progress = 50
            })
        }

        setContentView(root)
    }
}
