package com.bmk.data.data

import com.squareup.moshi.Json

data class DataResponse(
    @JvmField @Json(name = "id") val id: Int,
    @JvmField @Json(name = "completed") val completed: Boolean,
    @JvmField @Json(name = "title") val title: String,
    @JvmField @Json(name = "userId") val userId: Int
)

data class LoginRequest(
    @JvmField @Json(name = "userID") val id: String,
    @JvmField @Json(name = "password") val password: String,
)

data class LoginResponse(
    @JvmField @Json(name = "accessToken") val accessToken: String,
    @JvmField @Json(name = "refreshTime") val refreshTime: Long,
)