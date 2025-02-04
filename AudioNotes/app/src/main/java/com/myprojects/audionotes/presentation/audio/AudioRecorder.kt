// File: presentation/audio/AudioRecorder.kt

package com.myprojects.audionotes.presentation.audio

import android.media.MediaRecorder
import java.io.File

class AudioRecorder(private val outputFile: File) {

    private var mediaRecorder: MediaRecorder? = null

    fun startRecording() {
        mediaRecorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            setOutputFile(outputFile.absolutePath)
            prepare()
            start()
        }
    }

    fun stopRecording() {
        mediaRecorder?.apply {
            stop()
            release()
        }
        mediaRecorder = null
    }
}
