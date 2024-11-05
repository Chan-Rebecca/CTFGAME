package com.example.ctfgame

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * Login Activity allows the user to log in by entering their username and password.
 * On successful login, it navigates to the DashboardActivity.
 */
class Login : AppCompatActivity() {

    /**
     * Initializes the activity, sets up UI components, and handles login validation.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     * previously being shut down, this Bundle contains the saved data.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Link UI elements to variables by their ID
        val usernameInput: EditText = findViewById(R.id.loginUsernameInput)
        val passwordInput: EditText = findViewById(R.id.loginPasswordInput)
        val loginButton: Button = findViewById(R.id.loginButton)

        // Set up listener for the Login button
        loginButton.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            // Validate that both username and password fields are filled
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show()
            } else {
                // Authenticate credentials
                if (authenticateUser(username, password)) {
                    // Login successful
                    Toast.makeText(this, "Login successful!", Toast.LENGTH_LONG).show()

                    // Navigate to DashboardActivity
                    val intent = Intent(this, DashboardActivity::class.java)
                    startActivity(intent)
                    finish() // Close LoginActivity to remove it from back stack
                } else {
                    // Login failed
                    Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    /**
     * Authenticates the user by checking input credentials against stored credentials.
     *
     * @param username The entered username.
     * @param password The entered password.
     * @return Boolean indicating whether the credentials match stored data.
     */
    private fun authenticateUser(username: String, password: String): Boolean {
        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val storedUsername = sharedPreferences.getString("username", null)
        val storedPassword = sharedPreferences.getString("password", null)

        // Return true if both username and password match stored values
        return (username == storedUsername && password == storedPassword)
    }
}
