package com.bmk.data.mapper

import com.bmk.data.data.DataItem
import com.bmk.data.data.DataResponse
import com.bmk.data.doa.DoaService
import com.bmk.data.doa.TableData
import com.bmk.domain.Result
import com.bmk.domain.UserDetails
import retrofit2.Response as ApiResponse

fun ApiResponse<DataResponse>.mapper(doaService: DoaService): Result<List<UserDetails>> {
    try {
        val response = this
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {

                val userDetails = body.data.transform()
                doaService.insertAll(userDetails.transformDOA())
                return Result.Success(userDetails)
            }
        }
        return Result.Failure(Exception(" ${response.code()} ${response.message()}"))
    } catch (e: Exception) {

        return Result.Failure(Exception((e.message ?: e.toString())))
    }
}

fun List<DataItem>.transform(): List<UserDetails> {
    return map {
        UserDetails(
            id = it.id,
            email = it.email,
            lastName = it.lastName,
            firstName = it.firstName,
            avatar = it.avatar
        )
    }
}
fun List<UserDetails>.transformDOA(): List<TableData> {
    return map {
        TableData(
            id = it.id,
            email = it.email,
            lastName = it.lastName,
            firstName = it.firstName,
            avatar = it.avatar
        )
    }
}