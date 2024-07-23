package com.example.midterm_que1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val number1EditText = findViewById<EditText>(R.id.number1)
        val number2EditText = findViewById<EditText>(R.id.number2)
        val resultTextView = findViewById<TextView>(R.id.result)

        val buttonAdd = findViewById<Button>(R.id.buttonAdd)
        val buttonSubtract = findViewById<Button>(R.id.buttonSubtract)
        val buttonMultiply = findViewById<Button>(R.id.buttonMultiply)
        val buttonDivide = findViewById<Button>(R.id.buttonDivide)

        buttonAdd.setOnClickListener {
            performOperation(number1EditText, number2EditText, resultTextView, "add")
        }

        buttonSubtract.setOnClickListener {
            performOperation(number1EditText, number2EditText, resultTextView, "subtract")
        }

        buttonMultiply.setOnClickListener {
            performOperation(number1EditText, number2EditText, resultTextView, "multiply")
        }

        buttonDivide.setOnClickListener {
            performOperation(number1EditText, number2EditText, resultTextView, "divide")
        }
    }

    private fun performOperation(
        number1EditText: EditText,
        number2EditText: EditText,
        resultTextView: TextView,
        operation: String
    ) {
        val num1 = number1EditText.text.toString().toDoubleOrNull()
        val num2 = number2EditText.text.toString().toDoubleOrNull()

        if (num1 == null || num2 == null) {
            Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show()
            return
        }

        val result = when (operation) {
            "add" -> num1 + num2
            "subtract" -> num1 - num2
            "multiply" -> num1 * num2
            "divide" -> {
                if (num2 != 0.0) num1 / num2
                else {
                    Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show()
                    return
                }
            }
            else -> 0.0
        }

        resultTextView.text = result.toString()
    }
}
