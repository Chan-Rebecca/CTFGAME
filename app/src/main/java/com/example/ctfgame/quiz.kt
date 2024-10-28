package com.example.ctfgame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class QuizActivity : AppCompatActivity() {

    private val questions = listOf(
        Question("What is a VPN?", listOf("Virus Protection Network", "Virtual Private Network", "Video Private Network", "Voice Protocol Network"), 1),
        Question("What does a firewall do?", listOf("Increases speed", "Prevents hacking", "Filters traffic", "Stores data"), 2),
        Question("Which is a type of malware?", listOf("Phishing", "Spyware", "Cookies", "VPN"), 1)
    )

    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        // Link UI elements
        val questionText: TextView = findViewById(R.id.questionText)
        val answerOptions: RadioGroup = findViewById(R.id.answerOptions)
        val option1: RadioButton = findViewById(R.id.option1)
        val option2: RadioButton = findViewById(R.id.option2)
        val option3: RadioButton = findViewById(R.id.option3)
        val option4: RadioButton = findViewById(R.id.option4)
        val submitButton: Button = findViewById(R.id.submitAnswerButton)

        // Load the first question
        loadQuestion(questionText, option1, option2, option3, option4)

        // Handle Submit button click
        submitButton.setOnClickListener {
            val selectedOptionIndex = answerOptions.indexOfChild(findViewById(answerOptions.checkedRadioButtonId))
            if (selectedOptionIndex == questions[currentQuestionIndex].correctAnswerIndex) {
                score += 10
            }
            currentQuestionIndex++

            if (currentQuestionIndex < questions.size) {
                loadQuestion(questionText, option1, option2, option3, option4)
            } else {
                // End of quiz, show final score
                val intent = Intent(this, ScoreActivity::class.java)
                intent.putExtra("score", score)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun loadQuestion(questionText: TextView, option1: RadioButton, option2: RadioButton, option3: RadioButton, option4: RadioButton) {
        val question = questions[currentQuestionIndex]
        questionText.text = question.text
        option1.text = question.options[0]
        option2.text = question.options[1]
        option3.text = question.options[2]
        option4.text = question.options[3]
        findViewById<RadioGroup>(R.id.answerOptions).clearCheck()
    }
}

data class Question(val text: String, val options: List<String>, val correctAnswerIndex: Int)
