package com.citiustech.baseproject.data

import com.squareup.moshi.Json

data class DataResponse(
	@JvmField @Json(name="id") val id: Int,
	@JvmField @Json(name="completed") val completed: Boolean,
	@JvmField @Json(name="title") val title: String,
	@JvmField @Json(name="userId") val userId: Int
)
