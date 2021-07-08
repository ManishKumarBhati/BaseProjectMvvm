package com.citiustech.domain

interface Repository {
    suspend fun getData(): Result<Response>
}

data class Response(
    @JvmField val id: Int,
    @JvmField val completed: Boolean,
    @JvmField val title: String,
    @JvmField val userId: Int
)