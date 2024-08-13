package com.example.recipeapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recipeAdapter: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Sample Data
        val recipes = listOf(
            Recipe("Pasta", getString(R.string.pasta_recipe_details)),
            Recipe("Pizza", getString(R.string.pizza_recipe_details)),
            Recipe("Salad", getString(R.string.salad_recipe_details))
        )

        // Set up Adapter
        recipeAdapter = RecipeAdapter(recipes, ::onRecipeSelected)
        recyclerView.adapter = recipeAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add_recipe -> {
                // Open Add Recipe Activity (Implement as needed)
                true
            }
            R.id.action_settings -> {
                // Open Settings Fragment (Implement as needed)
                true
            }
            R.id.action_about -> {
                // Show About Dialog (Implement as needed)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun onRecipeSelected(recipe: Recipe) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            openSecondActivity(recipe)
        } else {
            permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    private val permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            recipeAdapter.selectedRecipe?.let { openSecondActivity(it) }
        } else {
            // Handle permission denial if needed
        }
    }

    private fun openSecondActivity(recipe: Recipe) {
        val intent = Intent(this, SecondActivity::class.java).apply {
            putExtra("RECIPE_NAME", recipe.name)
            putExtra("RECIPE_DETAILS", recipe.details)
        }
        startActivity(intent)
    }
}
