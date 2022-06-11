package com.example.lab_4

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.lab_4.databinding.ActivityBookInformationBinding

class BookInformationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookInformationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBookInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.textView3.text = intent.getStringExtra("category")
        binding.textView2.text = intent.getStringExtra("name")
    }
}