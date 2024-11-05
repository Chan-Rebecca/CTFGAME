package com.example.ctfgame

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * MainActivity handles user registration, allowing users to create an account with a username, email, and password.
 * On successful registration, it navigates to the LoginActivity.
 */
class MainActivity : AppCompatActivity() {

    /**
     * Initializes the activity, links UI elements, and sets up a listener for the submit button.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     * previously being shut down, this Bundle contains the saved data.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Link UI elements to variables by their ID
        val usernameInput: EditText = findViewById(R.id.usernameInput)
        val emailInput: EditText = findViewById(R.id.emailInput)
        val passwordInput: EditText = findViewById(R.id.passwordInput)
        val submitButton: Button = findViewById(R.id.submitButton)

        // Set up listener for the Submit button
        submitButton.setOnClickListener {
            val username = usernameInput.text.toString()
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            // Validate input fields
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

                // Navigate to LoginActivity
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()  // Close MainActivity to prevent navigating back to it
            }
        }
    }
}

/**
 * DashboardActivity serves as the main user interface after login, allowing the user to start a quiz, view the leaderboard, or log out.
 */
class DashboardActivity : AppCompatActivity() {

    /**
     * Initializes the activity, links UI elements, retrieves the username from SharedPreferences, and sets up button click listeners.
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

        // Retrieve username from SharedPreferences and display it
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

        // Set up listener for Logout button to clear session data and navigate back to LoginActivity
        logoutButton.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.clear() // Clear all stored user data (logging out)
            editor.apply()

            // Navigate back to LoginActivity and finish DashboardActivity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
