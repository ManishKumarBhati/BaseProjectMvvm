package com.citiustech.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("todos/{id}")
    suspend fun getData(@Path("id") id: String): Response<DataResponse>
}

