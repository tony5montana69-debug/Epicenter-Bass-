
package com.epicenter.bass

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.net.Uri
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.SeekBar
import android.widget.Button

class MainActivity : Activity() {

    private val musicPlayer = MusicPlayer()
    private val blue = Color.rgb(0, 160, 255)
    private var selectedSong: Uri? = null
    private lateinit var contentText: TextView

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

        fun addText(text: String, size: Float) {
            root.addView(TextView(this).apply {
                this.text = text
                textSize = size
                setTextColor(blue)
                gravity = Gravity.CENTER
                typeface = Typeface.DEFAULT_BOLD
                setPadding(5, 12, 5, 12)
            })
        }

        fun addButton(text: String, action: () -> Unit) {
            root.addView(Button(this).apply {
                this.text = text
                setOnClickListener { action() }
            })
        }

        addText("EPICENTER", 34f)
        addText("B A S S   B O O S T", 15f)

        contentText = TextView(this).apply {
            text = "NINGUNA CANCIÓN SELECCIONADA"
            textSize = 14f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
            setPadding(5, 20, 5, 20)
        }
        root.addView(contentText)

        addButton("SELECCIONAR CANCIÓN") {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "audio/*"
            }
            startActivityForResult(intent, 1001)
        }

        addButton("▶ REPRODUCIR") {
            selectedSong?.let {
                musicPlayer.play(this, it)
            }
        }

        addButton("⏸ PAUSAR") {
            musicPlayer.pause()
        }

        addButton("■ DETENER") {
            musicPlayer.stop()
        }

        addText("POTENCIA DE BAJOS", 18f)

        val bass = SeekBar(this).apply {
            max = 100
            progress = 50
        }
        root.addView(bass)

        val value = TextView(this).apply {
            text = "50%"
            textSize = 22f
            setTextColor(blue)
            gravity = Gravity.CENTER
        }
        root.addView(value)

        bass.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    value.text = "$progress%"
                    musicPlayer.setBass(progress)
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            }
        )

        setContentView(root)
    }

    @Deprecated("Deprecated in Android")
    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1001 && resultCode == RESULT_OK) {
            selectedSong = data?.data
            contentText.text = if (selectedSong != null) {
                "CANCIÓN SELECCIONADA"
            } else {
                "NINGUNA CANCIÓN SELECCIONADA"
            }
        }
    }

    override fun onDestroy() {
        musicPlayer.stop()
        super.onDestroy()
    }
}
