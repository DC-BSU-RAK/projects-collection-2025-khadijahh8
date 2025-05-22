package com.example.kittycareapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddCatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_cat)

        val nameInput = findViewById<EditText>(R.id.cat_name_input)
        val ageInput = findViewById<EditText>(R.id.cat_age_input)
        val breedInput = findViewById<EditText>(R.id.cat_breed_input)
        val saveButton = findViewById<Button>(R.id.button_save_cat)

        saveButton.setOnClickListener {
            val name = nameInput.text.toString()
            val age = ageInput.text.toString()
            val breed = breedInput.text.toString()

            if (name.isNotEmpty() && age.isNotEmpty() && breed.isNotEmpty()) {
                // Save to SharedPreferences
                val sharedPrefs = getSharedPreferences("CatPrefs", Context.MODE_PRIVATE)
                val editor = sharedPrefs.edit()
                editor.putString("cat_name", name)
                editor.putString("cat_age", age)
                editor.putString("cat_breed", breed)
                editor.apply()

                Toast.makeText(this, "Cat saved!", Toast.LENGTH_SHORT).show()

                // going back to homepage
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
