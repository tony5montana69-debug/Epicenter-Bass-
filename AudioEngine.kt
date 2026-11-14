
package com.epicenter.bass

import android.media.audiofx.BassBoost
import android.media.audiofx.Equalizer

class AudioEngine(sessionId: Int) {

    private var bassBoost: BassBoost? = null
    private var equalizer: Equalizer? = null

    init {
        try {
            bassBoost = BassBoost(0, sessionId).apply {
                enabled = true
                setStrength(700)
            }

            equalizer = Equalizer(0, sessionId).apply {
                enabled = true
            }
        } catch (e: Exception) {
            release()
        }
    }

    fun setBass(value: Int) {
        bassBoost?.setStrength(
            value.coerceIn(0, 1000).toShort()
        )
    }

    fun release() {
        bassBoost?.release()
        equalizer?.release()
        bassBoost = null
        equalizer = null
    }
}
