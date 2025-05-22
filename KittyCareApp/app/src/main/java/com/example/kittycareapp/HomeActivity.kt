package com.example.kittycareapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var sharedPrefs: android.content.SharedPreferences

    // Registering the photo picker launcher
    private val pickMedia = registerForActivityResult(PickVisualMedia()) { uri: Uri? ->
        if (uri != null) {
            Log.d("PhotoPicker", "Selected URI: $uri")
            imageView.setImageURI(uri)

            // Saving the URI to SharedPreferences
            sharedPrefs.edit().putString("cat_image_uri", uri.toString()).apply()
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        sharedPrefs = getSharedPreferences("CatPrefs", Context.MODE_PRIVATE)

        val name = sharedPrefs.getString("cat_name", null)
        val age = sharedPrefs.getString("cat_age", null)
        val breed = sharedPrefs.getString("cat_breed", null)
        val catInfoText = findViewById<TextView>(R.id.text_cat_info)

        if (name != null && age != null && breed != null) {
            catInfoText.text = "🐱 Your cat: $name, $age old, $breed"
        } else {
            catInfoText.text = "No cat info saved yet."
        }

        // Loading saved cat image URI (if exists)
        imageView = findViewById(R.id.image_cat)
        val savedUriString = sharedPrefs.getString("cat_image_uri", null)
        if (savedUriString != null) {
            imageView.setImageURI(Uri.parse(savedUriString))
        }

        // Button: Add Cat
        val addCatButton = findViewById<Button>(R.id.button_add_cat)
        addCatButton.setOnClickListener {
            val intent = Intent(this, AddCatActivity::class.java)
            startActivity(intent)
        }

        // Button: View Care Tips
        val careTipsButton = findViewById<Button>(R.id.button_care_tips)
        careTipsButton.setOnClickListener {
            val intent = Intent(this, CareTipsActivity::class.java)
            startActivity(intent)
        }

        // Button: Select Image
        val selectImageButton = findViewById<Button>(R.id.button_select_image)
        selectImageButton.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly))
        }

        // Instructions popup
        showInstructionsPopup()
    }

    private fun showInstructionsPopup() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Welcome to KittyCare! 🐾")
        builder.setMessage("To get started, add your cat’s details. Then you’ll receive personalized care tips to keep your kitty happy and healthy!")
        builder.setPositiveButton("Got it!") { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }
}
