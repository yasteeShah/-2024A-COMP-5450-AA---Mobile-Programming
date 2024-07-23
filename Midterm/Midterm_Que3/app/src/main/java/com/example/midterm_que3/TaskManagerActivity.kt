package com.example.midterm_que3

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class TaskManagerActivity : AppCompatActivity() {

    private lateinit var taskInput: EditText
    private lateinit var addButton: Button
    private lateinit var viewTasksButton: Button
    private lateinit var taskListView: ListView
    private val taskList = mutableListOf<String>()
    private lateinit var taskAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_manager)

        taskInput = findViewById(R.id.taskInput)
        addButton = findViewById(R.id.addButton)
        viewTasksButton = findViewById(R.id.viewTasksButton)
        taskListView = findViewById(R.id.taskListView)

        taskAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, taskList)
        taskListView.adapter = taskAdapter

        addButton.setOnClickListener {
            val task = taskInput.text.toString()
            if (task.isNotEmpty()) {
                taskList.add(task)
                taskAdapter.notifyDataSetChanged()
                taskInput.text.clear()
            }
        }

        viewTasksButton.setOnClickListener {
            // Pass the list of tasks to TaskListActivity
            val intent = Intent(this, TaskListActivity::class.java).apply {
                putStringArrayListExtra("task_list", ArrayList(taskList))
            }
            startActivity(intent)
        }
    }
}
