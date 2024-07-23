package com.example.midterm_que3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calculatorButton: Button = findViewById(R.id.calculatorButton)
        val taskManagerButton: Button = findViewById(R.id.taskManagerButton)

        calculatorButton.setOnClickListener {
            val intent = Intent(this, CalculatorActivity::class.java).apply {
                putExtra("launch_message", "Data from UtilityAppLauncher to Calculator")
            }
            startActivity(intent)
        }

        taskManagerButton.setOnClickListener {
            val intent = Intent(this, TaskManagerHomeActivity::class.java)
            startActivity(intent)
        }
    }
}
