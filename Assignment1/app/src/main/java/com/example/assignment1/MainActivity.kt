package com.example.assignment1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var themeSpinner: Spinner
    lateinit var shareQuoteBtn: Button
    lateinit var quoteTextView: TextView
    lateinit var selectedTheme: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        themeSpinner = findViewById(R.id.themeSpinner)
        shareQuoteBtn = findViewById(R.id.shareQuoteBtn)
        quoteTextView = findViewById(R.id.quoteTextView)

        // Setup the Spinner
        val themes = arrayOf("Inspirational", "Motivational", "Funny", "Life", "Love")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, themes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        themeSpinner.adapter = adapter
        themeSpinner.setPrompt("What type of Quote do you want??");

        // Spinner Item Selected Listener
        themeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedTheme = themes[position]
                displayQuote(selectedTheme)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        // Button Click Listener
        shareQuoteBtn.setOnClickListener {
            val intent = Intent(android.content.Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(android.content.Intent.EXTRA_TEXT, quoteTextView.text);
            intent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Quote Share")
            this.startActivity(Intent.createChooser(intent, "Share"));
        }
    }

    private fun displayQuote(theme: String) {
        val quotes = mapOf(
            "Inspirational" to "The best way to predict the future is to create it.",
            "Motivational" to "Don’t watch the clock; do what it does. Keep going.",
            "Funny" to "I'm not arguing, I'm just explaining why I'm right.",
            "Life" to "Life is what happens when you’re busy making other plans.",
            "Love" to "Love is not about how much you say ‘I love you’ but how much you prove that it’s true."
        )
        quoteTextView.text = quotes[theme]
    }
}
