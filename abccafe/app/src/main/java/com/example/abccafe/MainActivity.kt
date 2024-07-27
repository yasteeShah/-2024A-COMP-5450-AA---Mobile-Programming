package com.example.abccafe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_order -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, OrderFragment()).commit()
                    true
                }
                R.id.navigation_menu -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, MenuFragment()).commit()
                    true
                }
                else -> false
            }
        }

        // Set default selection
        navView.selectedItemId = R.id.navigation_order
    }
}
