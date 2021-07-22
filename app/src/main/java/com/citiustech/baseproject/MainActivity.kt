package com.citiustech.baseproject

import android.os.Bundle
import com.citiustech.baseproject.base.BaseActivity
import com.citiustech.baseproject.databinding.ActivityMainBinding
import com.citiustech.baseproject.navigator.Navigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.math.sqrt

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var navigator: Navigator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showShortToast("Hello Guest")
        calculateArea(5)
    }


    fun calculateArea(radius:Int): Double {
        val area=Math.PI * radius * radius
        return area
    }

}
