package com.citiustech.data

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("todos/1")
    suspend fun getData(): Response<DataResponse>
}

const val Base_Url = "https://jsonplaceholder.typicode.com/"