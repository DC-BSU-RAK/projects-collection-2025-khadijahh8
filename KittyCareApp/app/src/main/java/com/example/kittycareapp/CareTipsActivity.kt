package com.example.kittycareapp

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class CareTipsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_care_tips)

        val sharedPrefs = getSharedPreferences("CatPrefs", Context.MODE_PRIVATE)
        val name = sharedPrefs.getString("cat_name", "No name")
        val age = sharedPrefs.getString("cat_age", "No age")
        val breed = sharedPrefs.getString("cat_breed", "No breed")

    }
}
