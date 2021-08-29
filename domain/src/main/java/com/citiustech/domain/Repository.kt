package com.citiustech.domain

import androidx.lifecycle.LiveData

interface Repository {
    suspend fun getData(id: String): Result<Response>
    fun getLocalData(id: String): LiveData<Response>
    suspend fun update(id: Int, title: String)
    suspend fun delete(id: Int)
    suspend fun login(id: String, password: String): Result<LoginDataResponse>
    suspend fun refresh(): Result<LoginDataResponse>

}

data class Response(
    @JvmField val id: Int,
    @JvmField val completed: Boolean,
    @JvmField val title: String,
    @JvmField val userId: Int
)

data class LoginDataResponse(
    @JvmField val accessToken: String,
    @JvmField val refreshTime: Long,
)