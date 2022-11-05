package com.bmk.data

import com.bmk.data.data.DataResponse
import com.bmk.data.data.LoginRequest
import com.bmk.data.data.LoginResponse
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

