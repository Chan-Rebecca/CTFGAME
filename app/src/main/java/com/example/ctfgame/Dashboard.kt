package com.example.ctfgame

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * Dashboard Activity for displaying a welcome message to the user, and providing options to
 * start a quiz, view the leaderboard, or log out.
 */
class Dashboard : AppCompatActivity() {

    /**
     * Initializes the activity and sets up UI components and click listeners.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     * previously being shut down, this Bundle contains the saved data.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Link UI elements to variables by their ID
        val welcomeMessage: TextView = findViewById(R.id.welcomeMessage)
        val startQuizButton: Button = findViewById(R.id.startQuizButton)
        val viewLeaderboardButton: Button = findViewById(R.id.viewLeaderboardButton)
        val logoutButton: Button = findViewById(R.id.logoutButton)

        // Retrieve username from SharedPreferences and set welcome message
        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username", "") ?: ""
        welcomeMessage.text = "Welcome, $username!"

        // Set up listener for Start Quiz button to navigate to QuizActivity
        startQuizButton.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }

        // Set up listener for View Leaderboard button to navigate to LeaderboardActivity
        viewLeaderboardButton.setOnClickListener {
            val intent = Intent(this, LeaderboardActivity::class.java)
            startActivity(intent)
        }

        // Set up listener for Logout button to clear user data and return to LoginActivity
        logoutButton.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.clear() // Clear all stored user data from SharedPreferences
            editor.apply()
            
            // Start LoginActivity and finish Dashboard to prevent back navigation to this screen
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
