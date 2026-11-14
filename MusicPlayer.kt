
package com.epicenter.bass

import android.content.Context
import android.net.Uri
import android.media.MediaPlayer

class MusicPlayer {

    private var player: MediaPlayer? = null
    private var audioEngine: AudioEngine? = null

    fun play(context: Context, uri: Uri) {
        stop()

        try {
            val music = MediaPlayer()
            player = music

            music.setDataSource(context, uri)

            music.setOnPreparedListener {
                audioEngine = AudioEngine(it.audioSessionId)
                it.start()
            }

            music.setOnCompletionListener {
                stop()
            }

            music.prepareAsync()

        } catch (e: Exception) {
            stop()
        }
    }

    fun setBass(value: Int) {
        audioEngine?.setBass(
            (value.coerceIn(0, 100) * 10)
        )
    }

    fun stop() {
        audioEngine?.release()
        audioEngine = null

        player?.release()
        player = null
    }
}
