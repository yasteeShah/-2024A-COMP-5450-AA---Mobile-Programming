package com.example.midterm_que3

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class TaskListActivity : AppCompatActivity() {

    private lateinit var taskListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list)

        taskListView = findViewById(R.id.taskListView)

        // Retrieve the list of tasks from the Intent
        val taskList = intent.getStringArrayListExtra("task_list") ?: ArrayList()

        val taskAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, taskList)
        taskListView.adapter = taskAdapter
    }
}
