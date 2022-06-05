package com.example.testwildberries.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testwildberries.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_TestWildberries)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}