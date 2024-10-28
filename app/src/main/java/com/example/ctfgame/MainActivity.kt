package com.example.ctfgame


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Link UI elements
        val usernameInput: EditText = findViewById(R.id.usernameInput)
        val emailInput: EditText = findViewById(R.id.emailInput)
        val passwordInput: EditText = findViewById(R.id.passwordInput)
        val submitButton: Button = findViewById(R.id.submitButton)

        // Handle button click
        submitButton.setOnClickListener {
            val username = usernameInput.text.toString()
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            } else {
                // Store user details in SharedPreferences
                val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("username", username)
                editor.putString("email", email)
                editor.putString("password", password)
                editor.apply()

                Toast.makeText(this, "Account Created!", Toast.LENGTH_LONG).show()

                // Navigate to login screen
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Link UI elements
        val welcomeMessage: TextView = findViewById(R.id.welcomeMessage)
        val startQuizButton: Button = findViewById(R.id.startQuizButton)
        val viewLeaderboardButton: Button = findViewById(R.id.viewLeaderboardButton)
        val logoutButton: Button = findViewById(R.id.logoutButton)

        // Retrieve username from SharedPreferences and display it
        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username", "")
        welcomeMessage.text = "Welcome, $username!"

        // Handle Start Quiz button click
        startQuizButton.setOnClickListener {
            // Navigate to QuizActivity (to be created)
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }

        // Handle View Leaderboard button click
        viewLeaderboardButton.setOnClickListener {
            // Navigate to LeaderboardActivity (to be created)
            val intent = Intent(this, LeaderboardActivity::class.java)
            startActivity(intent)
        }

        // Handle Logout button click
        logoutButton.setOnClickListener {
            // Clear user session data and navigate back to LoginActivity
            val editor = sharedPreferences.edit()
            editor.clear()  // Clear all stored data (logging out)
            editor.apply()

            // Navigate back to LoginActivity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()  // Close DashboardActivity so user can't go back to it
        }
    }
}
