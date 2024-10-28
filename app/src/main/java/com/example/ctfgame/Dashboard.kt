package com.example.ctfgame


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Dashboard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Link UI elements
        val welcomeMessage: TextView = findViewById(R.id.welcomeMessage)
        val startQuizButton: Button = findViewById(R.id.startQuizButton)
        val viewLeaderboardButton: Button = findViewById(R.id.viewLeaderboardButton)
        val logoutButton: Button = findViewById(R.id.logoutButton)

        // Retrieve and display the username
        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username", "")
        welcomeMessage.text = "Welcome, $username!"

        // Start Quiz button click
        startQuizButton.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }

        // View Leaderboard button click
        viewLeaderboardButton.setOnClickListener {
            val intent = Intent(this, LeaderboardActivity::class.java)
            startActivity(intent)
        }

        // Logout button click
        logoutButton.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.clear() // Clear all stored data
            editor.apply()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
