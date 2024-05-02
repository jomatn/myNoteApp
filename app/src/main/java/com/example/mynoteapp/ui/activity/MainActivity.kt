package com.example.mynoteapp.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mynoteapp.R

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBindding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = MainActivityBinding.inflate(inflater, container, false)

    }
}