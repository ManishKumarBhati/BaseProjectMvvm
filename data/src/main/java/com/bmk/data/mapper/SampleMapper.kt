package com.bmk.data.mapper

import com.bmk.data.data.DataResponse
import com.bmk.data.data.LoginResponse
import com.bmk.data.doa.DoaService
import com.bmk.data.doa.TableData
import com.bmk.data.network.SessionManager
import com.bmk.domain.LoginDataResponse
import com.bmk.domain.Response
import com.bmk.domain.Result
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

fun ApiResponse<LoginResponse>.mapperLogin(
    sessionManager: SessionManager
): Result<LoginDataResponse> {
    try {
        val response = this
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                val data = LoginDataResponse(
                    body.accessToken,
                    body.refreshTime
                )
                sessionManager.saveAuthToken(body.accessToken, body.refreshTime)

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