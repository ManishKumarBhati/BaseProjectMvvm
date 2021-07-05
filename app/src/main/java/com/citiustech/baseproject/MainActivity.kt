package com.citiustech.baseproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.citiustech.baseproject.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Timber.d(binding.tvText.text.toString())
    }
}
