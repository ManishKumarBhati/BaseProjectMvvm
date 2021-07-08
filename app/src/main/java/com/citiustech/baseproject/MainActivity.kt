package com.citiustech.baseproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.citiustech.baseproject.databinding.ActivityMainBinding
import com.citiustech.baseproject.navigator.Navigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    @Inject lateinit var navigator: Navigator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
