package com.example.midterm_que3

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CalculatorActivity : AppCompatActivity() {

    private lateinit var input1: EditText
    private lateinit var input2: EditText
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        input1 = findViewById(R.id.input1)
        input2 = findViewById(R.id.input2)
        resultTextView = findViewById(R.id.resultTextView)

        val addButton: Button = findViewById(R.id.addButton)
        val subtractButton: Button = findViewById(R.id.subtractButton)
        val multiplyButton: Button = findViewById(R.id.multiplyButton)
        val divideButton: Button = findViewById(R.id.divideButton)

        addButton.setOnClickListener { performOperation(Operation.ADD) }
        subtractButton.setOnClickListener { performOperation(Operation.SUBTRACT) }
        multiplyButton.setOnClickListener { performOperation(Operation.MULTIPLY) }
        divideButton.setOnClickListener { performOperation(Operation.DIVIDE) }

        // Retrieve data from Intent
        val launchMessage = intent.getStringExtra("launch_message")
        launchMessage?.let {
            resultTextView.text = "Received: $it"
        }
    }

    private fun performOperation(operation: Operation) {
        val num1 = input1.text.toString().toDoubleOrNull() ?: 0.0
        val num2 = input2.text.toString().toDoubleOrNull() ?: 0.0
        val result = when (operation) {
            Operation.ADD -> num1 + num2
            Operation.SUBTRACT -> num1 - num2
            Operation.MULTIPLY -> num1 * num2
            Operation.DIVIDE -> if (num2 != 0.0) num1 / num2 else "Cannot divide by zero"
        }
        resultTextView.text = result.toString()
    }

    private enum class Operation {
        ADD, SUBTRACT, MULTIPLY, DIVIDE
    }
}
