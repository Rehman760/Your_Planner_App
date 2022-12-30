package com.example.projectplanner

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.PrintStream

class add : AppCompatActivity() {
    //var count:Int=1;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
    }

    fun view(view: View) {
        val intent: Intent = Intent(this, viewPlan::class.java)
        startActivity(intent)
        finish()
    }

    fun add(view: View) {
        val description: TextView = findViewById(R.id.taskDescription)
        val ps = PrintStream(openFileOutput("file.txt", MODE_APPEND))
        ps.println(description.text)
        ps.close()
        //count++
        Toast.makeText(this, "Task has been added", Toast.LENGTH_SHORT).show()
        description.text = ""
    }
}