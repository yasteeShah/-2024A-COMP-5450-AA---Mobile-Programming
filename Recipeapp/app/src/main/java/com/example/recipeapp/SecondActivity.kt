package com.example.recipeapp

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.ActivityCompat
import android.widget.ImageView
import android.widget.TextView
import android.widget.Button
import android.util.Log

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // Get the passed recipe name and details
        val recipeName = intent.getStringExtra("RECIPE_NAME")
        val recipeDetails = intent.getStringExtra("RECIPE_DETAILS")

        // Check if recipeName and recipeDetails are not null
        if (recipeName == null || recipeDetails == null) {
            Log.e("SecondActivity", "Recipe name or details are null")
            return
        }

        // Set the recipe details to the TextViews
        findViewById<TextView>(R.id.recipeNameTextView).text = recipeName
        findViewById<TextView>(R.id.recipeDetailsTextView).text = recipeDetails

        // Set a mock profile picture (you can replace this with a real image later)
        findViewById<ImageView>(R.id.profileImageView).setImageResource(R.drawable.pasta)

        // Set the appropriate image for the recipe
        val recipeImageView = findViewById<ImageView>(R.id.recipeImageView)
        val imageResource = when (recipeName) {
            "Pasta" -> R.drawable.pasta
            "Pizza" -> R.drawable.pizza
            "Salad" -> R.drawable.salad
            else -> R.drawable.home // Fallback image
        }

        recipeImageView.setImageResource(imageResource)

        // Create a notification channel
        createNotificationChannel()

        // Show a notification with the recipe name
        val notification = NotificationCompat.Builder(this, "recipe_channel")
            .setSmallIcon(R.drawable.ic_recipe_notification)
            .setContentTitle("Recipe Selected")
            .setContentText("You selected the $recipeName recipe.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        NotificationManagerCompat.from(this).notify(1, notification)

        // Set up the back button to return to MainActivity
        findViewById<Button>(R.id.backButton).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Recipe Channel"
            val descriptionText = "Channel for Recipe Notifications"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("recipe_channel", name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
