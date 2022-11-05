package com.bmk.baseproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bmk.baseproject.util.Data
import com.bmk.baseproject.util.Status
import com.bmk.domain.Repository
import com.bmk.domain.Response
import com.bmk.domain.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SampleViewModel @Inject constructor(private val repository: Repository) :
    BaseViewModel() {

    fun getData(): MutableLiveData<Data<Response>> {
        val mutableMainState = MutableLiveData<Data<Response>>()
        viewModelScope.launch {
            val id = (1..9).random().toString()

            mutableMainState.value = Data(responseType = Status.LOADING, error = null, data = null)
            when (val result = withContext(Dispatchers.IO) { repository.getData(id) }) {
                is Result.Failure -> {
                    mutableMainState.value =
                        Data(responseType = Status.ERROR, error = result.exception, data = null)
                }
                is Result.Success -> {
                    mutableMainState.value =
                        Data(responseType = Status.SUCCESSFUL, data = result.data)
                }
            }
        }
        return mutableMainState
    }

    fun updateData(id: Int, title: String) = viewModelScope.launch { repository.update(id, title) }
    fun delete(id: Int) = viewModelScope.launch { repository.delete(id) }
    fun getLocalData() = repository.getLocalData((1..9).random().toString())
    override fun onCleared() {
        TODO("Not yet implemented")
    }
}