package com.citiustech.baseproject.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment : Fragment() {
    @JvmField
    protected val disposable: CompositeDisposable = CompositeDisposable()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayout(), container, false)
    }
    @LayoutRes
    abstract fun getLayout(): Int
    abstract override fun onViewCreated(view: View, savedInstanceState: Bundle?)
}