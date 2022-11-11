package com.bmk.baseproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.bmk.baseproject.util.ResponseState
import com.bmk.domain.Repository
import com.bmk.domain.Result
import com.bmk.domain.UserDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.security.interfaces.RSAKey
import javax.inject.Inject

@HiltViewModel
class SampleViewModel @Inject constructor(private val repository: Repository) :
    BaseViewModel() {
    val mutableMainState = MutableLiveData<ResponseState<List<UserDetails>>>()

    fun getData() {
        viewModelScope.launch {
            mutableMainState.value = ResponseState.Loading
            when (val result = withContext(Dispatchers.IO) { repository.getData("20") }) {
                is Result.Failure -> mutableMainState.value = ResponseState.Error(result.exception)
                is Result.Success -> mutableMainState.value = ResponseState.Success(result.data)
            }
        }
    }

    fun updateData(id: Int, title: String) = viewModelScope.launch { repository.update(id, title) }
    fun delete(id: Int) = viewModelScope.launch { repository.delete(id) }
    fun getLocalData() {
        repository.getUser((1..9).random().toString())
    }

    fun getAllUser() {
        repository.getAllUserDetails().map {
            mutableMainState.value = ResponseState.Success(it)
        }

    }


}