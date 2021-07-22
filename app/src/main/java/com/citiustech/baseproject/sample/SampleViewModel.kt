package com.citiustech.baseproject.sample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.citiustech.baseproject.util.Data
import com.citiustech.baseproject.util.Status
import com.citiustech.domain.Repository
import com.citiustech.domain.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SampleViewModel @Inject constructor(private val repository: Repository) :
    ViewModel() {
    private var mutableMainState: MutableLiveData<Data<*>> = MutableLiveData()
    val mainState: LiveData<Data<*>>
        get() {
            return mutableMainState
        }

    init {
        getData()
    }

    fun getData() {
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
    }

    fun getLocalData() = repository.getLocalData((1..9).random().toString())
}