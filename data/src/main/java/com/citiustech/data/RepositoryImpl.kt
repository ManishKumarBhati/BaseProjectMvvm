package com.citiustech.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.citiustech.data.db.DoaService
import com.citiustech.domain.Repository
import com.citiustech.domain.Response
import com.citiustech.domain.Result
import javax.inject.Inject


class RepositoryImpl @Inject constructor(val api: ApiService, val doaService: DoaService) :
    Repository {
    override suspend fun getData(id: String): Result<Response> {
        return api.getData(id).mapper(doaService)
    }

    override fun getLocalData(id: String): LiveData<Response> {
        return Transformations.map(doaService.getData()) {

            if (it == null) Response(
                id = -1,
                completed = false,
                title = "No Data Available",
                userId = -1
            )
            else Response(
                id = it.id,
                completed = it.completed,
                title = it.title,
                userId = it.userId
            )

        }
    }

    override suspend fun update(id: Int, title: String) {
        doaService.update(id, title)
    }

    override suspend fun delete(id: Int) {
        doaService.delete(id)
    }
}