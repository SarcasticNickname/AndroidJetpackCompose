// File: presentation/audio/AudioPlayer.kt

package com.myprojects.audionotes.presentation.audio

import android.media.MediaPlayer
import java.io.File

/**
 * Класс, демонстрирующий упрощённую работу с MediaPlayer.
 */
class AudioPlayer(private val audioFile: File) {

    private var mediaPlayer: MediaPlayer? = null

    fun startPlayback() {
        mediaPlayer = MediaPlayer().apply {
            setDataSource(audioFile.absolutePath)
            prepare()
            start()
        }
    }

    fun stopPlayback() {
        mediaPlayer?.apply {
            stop()
            release()
        }
        mediaPlayer = null
    }
}
