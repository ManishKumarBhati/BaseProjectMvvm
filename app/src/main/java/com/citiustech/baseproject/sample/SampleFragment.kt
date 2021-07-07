package com.citiustech.baseproject.sample

import android.os.Bundle
import android.view.View
import com.citiustech.baseproject.R
import com.citiustech.baseproject.base.BaseFragment
import com.citiustech.baseproject.databinding.FragmentSampleBinding
import com.citiustech.domain.Repository
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class SampleFragment : BaseFragment() {
    lateinit var binding: FragmentSampleBinding

    @Inject
    lateinit var repository: Repository

    override fun getLayout(): Int {
        return R.layout.fragment_sample
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding = FragmentSampleBinding.bind(view)
        repository.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                Timber.d("bmk $response")
            }, { error ->
                Timber.e("Error ${error.localizedMessage}")
            }).addTo(disposable)
    }

    companion object {
        const val TAG = "SapmleFrag"
    }
}