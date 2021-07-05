package com.citiustech.baseproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.citiustech.baseproject.databinding.ActivityMainBinding
import com.citiustech.baseproject.navigator.NavigationConstant
import com.citiustech.baseproject.navigator.Navigator
import com.citiustech.baseproject.sample.SampleFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    @Inject lateinit var navigator: Navigator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setView()
    }
    private fun setView() {
        navigator.openPage(NavigationConstant.NavigateToSampleFragment,Bundle().apply { putString("bmk","leo") })
    }

}
