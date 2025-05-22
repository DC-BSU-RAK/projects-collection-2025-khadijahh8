package com.example.emotiforecast

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.PopupWindow
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {

    private lateinit var spinnerEmotion1: Spinner
    private lateinit var spinnerEmotion2: Spinner
    private lateinit var forecastTextView: TextView
    private lateinit var forecastButton: Button
    private lateinit var infoButton: Button
    private lateinit var mainLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Link Kotlin variables to XML views
        spinnerEmotion1 = findViewById(R.id.spinnerEmotion1)
        spinnerEmotion2 = findViewById(R.id.spinnerEmotion2)
        forecastTextView = findViewById(R.id.forecastTextView)
        forecastButton = findViewById(R.id.forecastButton)
        infoButton = findViewById(R.id.infoButton)
        mainLayout = findViewById(R.id.main)

        // Emotions List
        val emotions = listOf(
            "Happy", "Sad", "Calm", "Excited",
            "Angry", "Curious", "Hopeful", "Anxious"
        )

        // Spinner adapter
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, emotions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerEmotion1.adapter = adapter
        spinnerEmotion2.adapter = adapter

        // Forecast Button Click
        forecastButton.setOnClickListener {
            val emotion1 = spinnerEmotion1.selectedItem.toString()
            val emotion2 = spinnerEmotion2.selectedItem.toString()
            val forecast = generateForecast(emotion1, emotion2)
            forecastTextView.text = forecast
        }

        // Info Button Click - shows popup
        infoButton.setOnClickListener {
            showInfoPopup()
        }
    }

    private fun generateForecast(emotion1: String, emotion2: String): String {
        return when {
            (emotion1 == "Happy" && emotion2 == "Excited") || (emotion1 == "Excited" && emotion2 == "Happy") -> "Sunny with fireworks 🎆☀️"
            (emotion1 == "Sad" && emotion2 == "Anxious") || (emotion1 == "Anxious" && emotion2 == "Sad") -> "Stormy skies with thunder 🌩️☔"
            (emotion1 == "Calm" && emotion2 == "Hopeful") || (emotion1 == "Hopeful" && emotion2 == "Calm") -> "Clear skies and gentle breezes 🌤️🍃"
            (emotion1 == "Angry" && emotion2 == "Curious") || (emotion1 == "Curious" && emotion2 == "Angry") -> "Windy with sudden lightning ⚡🌪️"
            else -> "Partly cloudy with emotional surprises 🌥️💭"
        }
    }

    private fun showInfoPopup() {
        // Inflate the popup layout
        val inflater = layoutInflater
        val popupView = inflater.inflate(R.layout.popup, null)

        // Create the popup window
        val popupWindow = PopupWindow(
            popupView,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            true
        )

        // Show the popup at the center of the screen
        popupWindow.showAtLocation(mainLayout, Gravity.CENTER, 0, 0)

        // Handle close button inside popup
        val closeButton: Button = popupView.findViewById(R.id.closeButton)
        closeButton.setOnClickListener {
            popupWindow.dismiss()
        }
    }
}
