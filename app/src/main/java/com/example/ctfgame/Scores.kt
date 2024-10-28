package com.example.ctfgame

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ScoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        // Retrieve the score from the intent
        val score = intent.getIntExtra("score", 0)

        // Display the score
        val scoreText: TextView = findViewById(R.id.scoreText)
        scoreText.text = "Your Score: $score"

        // Save the score in SharedPreferences
        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("score", score)
        editor.apply()

        // Return to Dashboard button
        val returnButton: Button = findViewById(R.id.returnButton)
        returnButton.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
