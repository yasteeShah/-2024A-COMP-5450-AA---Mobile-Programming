package com.example.midterm_que2

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var taskInput: EditText
    private lateinit var prioritySpinner: Spinner
    private lateinit var importantCheckBox: CheckBox
    private lateinit var statusRadioGroup: RadioGroup
    private lateinit var addTaskButton: Button
    private lateinit var viewTasksButton: Button
    private val tasks = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        taskInput = findViewById(R.id.taskInput)
        prioritySpinner = findViewById(R.id.prioritySpinner)
        importantCheckBox = findViewById(R.id.importantCheckBox)
        statusRadioGroup = findViewById(R.id.statusRadioGroup)
        addTaskButton = findViewById(R.id.addTaskButton)
        viewTasksButton = findViewById(R.id.viewTasksButton)

        addTaskButton.setOnClickListener {
            addTask()
        }

        viewTasksButton.setOnClickListener {
            viewTasks()
        }

        prioritySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                // Optionally handle item selection changes
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Optionally handle no item selected case
            }
        }
    }

    private fun addTask() {
        val task = taskInput.text.toString().trim()
        val priority = prioritySpinner.selectedItem.toString()
        val important = if (importantCheckBox.isChecked) "Important" else "Not Important"
        val status = when (statusRadioGroup.checkedRadioButtonId) {
            R.id.notStartedRadioButton -> "Not Started"
            R.id.inProgressRadioButton -> "In Progress"
            R.id.completedRadioButton -> "Completed"
            else -> "Unknown"
        }

        if (task.isNotEmpty()) {
            tasks.add("$task (Priority: $priority, $important, Status: $status)")
            taskInput.text.clear()
            importantCheckBox.isChecked = false
            statusRadioGroup.clearCheck()
        }
    }

    private fun viewTasks() {
        val intent = Intent(this, TaskListActivity::class.java)
        intent.putStringArrayListExtra("tasks", ArrayList(tasks))
        startActivity(intent)
    }
}
