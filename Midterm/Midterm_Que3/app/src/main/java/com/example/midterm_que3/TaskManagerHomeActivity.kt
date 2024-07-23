package com.example.midterm_que3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class TaskManagerHomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_manager_home)

        val addTaskButton: Button = findViewById(R.id.addTaskButton)
        addTaskButton.setOnClickListener {
            // Launch TaskManagerActivity to add tasks
            val intent = Intent(this, TaskManagerActivity::class.java)
            startActivity(intent)
        }
    }
}
