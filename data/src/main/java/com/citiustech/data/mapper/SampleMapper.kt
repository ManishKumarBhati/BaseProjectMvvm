package com.citiustech.data.mapper

import com.citiustech.data.data.DataResponse
import com.citiustech.data.data.LoginResponse
import com.citiustech.data.doa.DoaService
import com.citiustech.data.doa.TableData
import com.citiustech.data.network.SessionManager
import com.citiustech.domain.LoginDataResponse
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