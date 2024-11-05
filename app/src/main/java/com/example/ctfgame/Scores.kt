package com.example.ctfgame

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * ScoreActivity displays the user's quiz score and allows them to return to the dashboard.
 * The score is saved in SharedPreferences for later access, such as viewing the leaderboard.
 */
class ScoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        // Retrieve the score from the intent that started this activity
        val score = intent.getIntExtra("score", 0)

        // Display the score in a TextView
        val scoreText: TextView = findViewById(R.id.scoreText)
        scoreText.text = "Your Score: $score"

        // Save the score in SharedPreferences for persistence
        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("score", score)  // Store the score with key "score"
        editor.apply()  // Apply changes asynchronously

        // Link the "Return to Dashboard" button
        val returnButton: Button = findViewById(R.id.returnButton)
        returnButton.setOnClickListener {
            // Start DashboardActivity and close the current activity
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish()  // Close ScoreActivity to prevent it from being on the back stack
        }
    }
}
