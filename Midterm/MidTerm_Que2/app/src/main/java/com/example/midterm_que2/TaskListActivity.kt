package com.example.midterm_que2

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TaskListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list)

        val tasksListTextView: TextView = findViewById(R.id.tasksList)

        val tasks = intent.getStringArrayListExtra("tasks") ?: arrayListOf()
        if (tasks.isNotEmpty()) {
            tasksListTextView.text = tasks.joinToString("\n")
        } else {
            tasksListTextView.text = "No tasks added yet."
        }
    }
}


