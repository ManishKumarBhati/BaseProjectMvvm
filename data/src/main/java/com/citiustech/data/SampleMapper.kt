package com.citiustech.data

import com.citiustech.domain.Response
import com.citiustech.domain.Result
import retrofit2.Response as ApiResponse

fun ApiResponse<DataResponse>.mapper(): Result<Response> {
    try {
        val response = this
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) return Result.Success(body.transform())
        }
        return Result.Failure(Exception(" ${response.code()} ${response.message()}"))
    } catch (e: Exception) {
        return Result.Failure(Exception((e.message ?: e.toString())))
    }
}

fun DataResponse.transform(): Response {
    return Response(
        id = id,
        completed = completed,
        title = title,
        userId = userId
    )
}