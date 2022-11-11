package com.bmk.data

import com.bmk.data.data.DataResponse

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun getData(@Query("per_page") pageId: String): Response<DataResponse>
}

