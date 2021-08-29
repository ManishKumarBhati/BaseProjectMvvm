package com.citiustech.data

import com.citiustech.data.data.DataResponse
import com.citiustech.data.data.LoginRequest
import com.citiustech.data.data.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("todos/{id}")
    suspend fun getData(@Path("id") id: String): Response<DataResponse>

    //dummy api
    @POST("Login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("RefreshToken")
    suspend fun refresh(@Body request: LoginRequest): Response<LoginResponse>
}

