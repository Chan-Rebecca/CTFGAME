package com.example.ctfgame


import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LeaderboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)

        // Retrieve and display the score from SharedPreferences
        val leaderboardText: TextView = findViewById(R.id.leaderboardText)
        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username", "")
        val score = sharedPreferences.getInt("score", 0)

        leaderboardText.text = "Top Scores:\n1. $username - $score"
    }
}
