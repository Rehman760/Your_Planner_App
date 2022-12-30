package com.example.projectplanner

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    var speak: Boolean = false
    var data = ""
    lateinit var btnSpeak: Button
    lateinit var tts: TextToSpeech
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnSpeak = findViewById(R.id.listen)
        btnSpeak.isEnabled = false
        tts = TextToSpeech(this, this)

    }

    fun add(view: View) {
        val intent: Intent = Intent(this, add::class.java)
        startActivity(intent)

    }

    fun view(view: View) {
        val intent: Intent = Intent(this, viewPlan::class.java)
        startActivity(intent)

    }

    fun listen(view: View) {

        try {
            val sc = Scanner(openFileInput("file.txt"))

            while (sc.hasNextLine())
                data += sc.nextLine() + "\n"
        } catch (e: Exception) {

        }
        tts.speak(data, TextToSpeech.QUEUE_FLUSH, null, "")
        Toast.makeText(this, "Plan will be explained", Toast.LENGTH_SHORT).show()

    }

    override fun onInit(status: Int) {

        if (status == TextToSpeech.SUCCESS) {
            val result = tts!!.setLanguage(Locale.US)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "The Language not supported!")
            } else {
                btnSpeak!!.isEnabled = true
            }
        }
    }

}