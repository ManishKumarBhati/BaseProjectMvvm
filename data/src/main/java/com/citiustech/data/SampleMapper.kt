package com.citiustech.data

import com.citiustech.data.db.DoaService
import com.citiustech.data.db.TableData
import com.citiustech.domain.Response
import com.citiustech.domain.Result
import retrofit2.Response as ApiResponse

fun ApiResponse<DataResponse>.mapper(doaService: DoaService): Result<Response> {
    try {
        val response = this
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                val data = body.transform()
                doaService.insertAll(
                    TableData(
                        id = data.id,
                        completed = data.completed,
                        title = data.title,
                        userId = data.userId
                    )
                )
                return Result.Success(data)
            }
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