package com.citiustech.data

import com.citiustech.domain.Repository
import com.citiustech.domain.Response
import io.reactivex.Observable
import javax.inject.Inject


class RepositoryImpl @Inject constructor(val api: ApiService) : Repository {
    override fun getData(): Observable<Response> {
        return api.getData().map {
            Response(
                id = it.id,
                completed = it.completed,
                title = it.title,
                userId = it.userId
            )
        }
    }
}