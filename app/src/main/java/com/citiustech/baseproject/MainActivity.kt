package com.citiustech.baseproject

import android.os.Bundle
import com.citiustech.baseproject.base.BaseActivity
import com.citiustech.baseproject.databinding.ActivityMainBinding
import com.citiustech.baseproject.navigator.Navigator
import com.citiustech.baseproject.util.NetworkHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var networkHelper: NetworkHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        networkHelper.observe(this, {
            if (it) showShortToast("Network Available")
            else showShortToast("network not available")
        })

        calculateArea(5)
    }


    fun calculateArea(radius: Int): Double {
        val area = Math.PI * radius * radius
        return area
    }

}
