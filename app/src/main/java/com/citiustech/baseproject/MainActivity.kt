package com.citiustech.baseproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.citiustech.baseproject.databinding.ActivityMainBinding
import com.citiustech.baseproject.sample.SampleFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setView()
    }
    private fun setView() {
        supportFragmentManager.beginTransaction()
            .disallowAddToBackStack()
            .replace(R.id.frame, SampleFragment.newInstance(),SampleFragment.TAG)
            .commit()
    }

}
