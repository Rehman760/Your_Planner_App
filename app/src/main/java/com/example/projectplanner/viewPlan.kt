package com.example.projectplanner

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import java.io.PrintStream
import java.util.*

class viewPlan : AppCompatActivity() {
    var isTTSReady = false;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_plan)
        var arrayList = ArrayList<User>()

        try {
            val sc = Scanner(openFileInput("file.txt"))

            while (sc.hasNextLine())
                arrayList.add(User(R.drawable.tick, sc.nextLine()))
        } catch (e: Exception) {

        }
        val listView: ListView = findViewById(R.id.list)
        val adapter = MyListAdapter(this, R.layout.view_tasks, arrayList)
        val tts = TextToSpeech(
            this,
            TextToSpeech.OnInitListener {
                isTTSReady = true
            })
        listView.adapter = adapter

        listView.setOnItemClickListener(
            AdapterView.OnItemClickListener { adapterView, view, i, l ->

                if (isTTSReady)
                    tts.speak(
                        arrayList[i].name,
                        TextToSpeech.QUEUE_FLUSH,
                        null,
                        "1234"
                    )
            })


    }

    fun clear(view: View) {
        try {
            val ps = PrintStream(openFileOutput("file.txt", MODE_PRIVATE))
            ps.print("")
            ps.close()

        } catch (e: Exception) {

        }


        val intent: Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()

    }
}
