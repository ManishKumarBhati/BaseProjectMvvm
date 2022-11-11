package com.bmk.data.data

import com.squareup.moshi.Json


data class DataResponse(
    @JvmField @field:Json(name = "per_page") val perPage: Int,
    @JvmField @field:Json(name = "total") val total: Int,
    @JvmField @field:Json(name = "data") val data: List<DataItem>,
    @JvmField @field:Json(name = "p`age") val page: Int,
    @JvmField @field:Json(name = "total_pages") val totalPages: Int,
    @JvmField @field:Json(name = "support") val support: Support
)

data class DataItem(
    @JvmField @field:Json(name = "last_name") val lastName: String,
    @JvmField @field:Json(name = "id") val id: Int,
    @JvmField @field:Json(name = "avatar") val avatar: String,
    @JvmField @field:Json(name = "first_name") val firstName: String,
    @JvmField @field:Json(name = "email") val email: String
)

data class Support(
    @JvmField @field:Json(name = "text") val text: String,
    @JvmField @field:Json(name = "url") val url: String
)
