package com.example.ctfgame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * QuizActivity handles the quiz interface, displaying questions with multiple-choice answers.
 * It tracks the score and navigates to ScoreActivity at the end of the quiz.
 */
class QuizActivity : AppCompatActivity() {

    // List of questions with text, options, and the correct answer index
    private val questions = listOf(
        Question("What is a VPN?", listOf("Virus Protection Network", "Virtual Private Network", "Video Private Network", "Voice Protocol Network"), 1),
        Question("What does a firewall do?", listOf("Increases speed", "Prevents hacking", "Filters traffic", "Stores data"), 2),
        Question("Which is a type of malware?", listOf("Phishing", "Spyware", "Cookies", "VPN"), 1)
    )

    private var currentQuestionIndex = 0  // Track the current question index
    private var score = 0  // Track the user’s score

    /**
     * Initializes the activity, links UI elements, loads the first question, and sets up submit button functionality.
     *
     * @param savedInstanceState Bundle containing the activity's previously saved state.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        // Link UI elements to their IDs
        val questionText: TextView = findViewById(R.id.questionText)
        val answerOptions: RadioGroup = findViewById(R.id.answerOptions)
        val option1: RadioButton = findViewById(R.id.option1)
        val option2: RadioButton = findViewById(R.id.option2)
        val option3: RadioButton = findViewById(R.id.option3)
        val option4: RadioButton = findViewById(R.id.option4)
        val submitButton: Button = findViewById(R.id.submitAnswerButton)

        // Load the first question
        loadQuestion(questionText, option1, option2, option3, option4)

        // Set up listener for Submit button
        submitButton.setOnClickListener {
            // Check selected answer
            val selectedOptionIndex = answerOptions.indexOfChild(findViewById(answerOptions.checkedRadioButtonId))

            // Verify if the selected answer is correct
            if (selectedOptionIndex == questions[currentQuestionIndex].correctAnswerIndex) {
                score += 10  // Increment score by 10 for a correct answer
            }

            currentQuestionIndex++  // Move to the next question

            // Check if there are more questions
            if (currentQuestionIndex < questions.size) {
                loadQuestion(questionText, option1, option2, option3, option4)
            } else {
                // End of quiz, navigate to ScoreActivity to display the final score
                val intent = Intent(this, ScoreActivity::class.java)
                intent.putExtra("score", score)
                startActivity(intent)
                finish()  // Close QuizActivity to prevent going back
            }
        }
    }

    /**
     * Loads the current question and its answer options onto the screen.
     *
     * @param questionText TextView displaying the question text.
     * @param option1 First RadioButton displaying an answer option.
     * @param option2 Second RadioButton displaying an answer option.
     * @param option3 Third RadioButton displaying an answer option.
     * @param option4 Fourth RadioButton displaying an answer option.
     */
    private fun loadQuestion(questionText: TextView, option1: RadioButton, option2: RadioButton, option3: RadioButton, option4: RadioButton) {
        val question = questions[currentQuestionIndex]
        questionText.text = question.text  // Set the question text
        option1.text = question.options[0]
        option2.text = question.options[1]
        option3.text = question.options[2]
        option4.text = question.options[3]

        // Clear any previous selections
        findViewById<RadioGroup>(R.id.answerOptions).clearCheck()
    }
}

/**
 * Data class representing a Question with its text, list of options, and the correct answer index.
 *
 * @property text The question text.
 * @property options List of answer options.
 * @property correctAnswerIndex The index of the correct answer within the options list.
 */
data class Question(val text: String, val options: List<String>, val correctAnswerIndex: Int)
