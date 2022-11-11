package com.bmk.baseproject.fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bmk.baseproject.R
import com.bmk.baseproject.databinding.FragmentSampleBinding
import com.bmk.baseproject.helper.Helper
import com.bmk.baseproject.util.ResponseState
import com.bmk.baseproject.util.isOnline
import com.bmk.baseproject.viewmodel.SampleViewModel
import com.bmk.domain.Repository
import com.bmk.domain.UserDetails
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SampleFragment : BaseFragment() {
    lateinit var binding: FragmentSampleBinding

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var repository: Repository

    private val viewModel: SampleViewModel by viewModels()

    @Inject
    lateinit var helper: Helper
    override fun getLayout(): Int {
        return R.layout.fragment_sample
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentSampleBinding.bind(view)
        if (context?.isOnline() == true) viewModel.getData() else viewModel.getAllUser()
        viewModel.mutableMainState.observe(viewLifecycleOwner) {
            helper.toggleProgress(it is ResponseState.Loading)
            when (it) {
                is ResponseState.Error -> helper.showError(it.throwable)
                is ResponseState.Success -> setUpRecyclerView(it.data)
                else -> {}
            }
        }


    }

    private fun setUpRecyclerView(userDetails: List<UserDetails>) {
        binding.rvList.apply {
            adapter = UserAdapter(userDetails, ::setOnClickListener)
            layoutManager = LinearLayoutManager(context)
        }
    }

    fun setOnClickListener(userDetails: UserDetails) {
        val bundle = Bundle()
        bundle.putParcelable(SecondSampleFragment.ARGS_USER_DATA, userDetails)
        findNavController().navigate(R.id.nav_to_second_frag, bundle)

    }

}