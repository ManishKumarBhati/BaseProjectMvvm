package com.citiustech.data

import com.citiustech.domain.Repository
import com.citiustech.domain.Response
import com.citiustech.domain.Result
import javax.inject.Inject


class RepositoryImpl @Inject constructor(val api: ApiService) : Repository {
    override suspend fun getData(): Result<Response> {
        return api.getData().mapper()
    }
}