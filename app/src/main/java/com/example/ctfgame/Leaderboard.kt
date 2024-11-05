package com.example.ctfgame

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * LeaderboardActivity displays the leaderboard information, showing top scores
 * stored in SharedPreferences for the current user.
 */
class LeaderboardActivity : AppCompatActivity() {

    /**
     * Initializes the activity, retrieves the user's score from SharedPreferences,
     * and displays it on the leaderboard.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     * previously being shut down, this Bundle contains the saved data.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)

        // Retrieve leaderboard TextView to display scores
        val leaderboardText: TextView = findViewById(R.id.leaderboardText)

        // Access SharedPreferences to retrieve username and score
        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username", "") ?: ""
        val score = sharedPreferences.getInt("score", 0)

        // Display the retrieved username and score
        leaderboardText.text = "Top Scores:\n1. $username - $score"
    }
}
