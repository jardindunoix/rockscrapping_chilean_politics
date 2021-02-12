package com.example.rockscrappinchileanpolitics.ui.activities

import android.os.*
import androidx.appcompat.app.AppCompatActivity
import com.example.rockscrappinchileanpolitics.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "RockScrappin for Chilean Politics"

    }
}