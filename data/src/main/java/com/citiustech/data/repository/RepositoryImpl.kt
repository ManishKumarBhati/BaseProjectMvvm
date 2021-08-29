package com.citiustech.data.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.citiustech.data.ApiService
import com.citiustech.data.data.LoginRequest
import com.citiustech.data.doa.DoaService
import com.citiustech.data.mapper.mapper
import com.citiustech.data.mapper.mapperLogin
import com.citiustech.data.network.SessionManager
import com.citiustech.data.util.USER_ID
import com.citiustech.data.util.USER_PASS
import com.citiustech.domain.LoginDataResponse
import com.citiustech.domain.Repository
import com.citiustech.domain.Response
import com.citiustech.domain.Result
import javax.inject.Inject


class RepositoryImpl @Inject constructor(
    val api: ApiService,
    val doaService: DoaService,
    val sessionManager: SessionManager,
    val sharedPreferences: SharedPreferences
) :
    Repository {
    override suspend fun getData(id: String): Result<Response> {
        return api.getData(id).mapper(doaService)
    }

    override fun getLocalData(id: String): LiveData<Response> {
        return Transformations.map(doaService.getData()) {

            if (it == null) Response(
                id = -1,
                completed = false,
                title = "No Data Available",
                userId = -1
            )
            else Response(
                id = it.id,
                completed = it.completed,
                title = it.title,
                userId = it.userId
            )

        }
    }

    override suspend fun update(id: Int, title: String) {
        doaService.update(id, title)
    }

    override suspend fun delete(id: Int) {
        doaService.delete(id)
    }

    override suspend fun login(id: String, password: String): Result<LoginDataResponse> {
        val request = LoginRequest(id, password)
        val res = api.login(request).mapperLogin(sessionManager)
        if (res is Result.Success) {
            sharedPreferences.edit {
                putString(USER_ID, id)
                putString(USER_PASS, password)
            }
        }
        return res
    }

    override suspend fun refresh(): Result<LoginDataResponse> {
        val id = sharedPreferences.getString(USER_ID, "")
        val pass = sharedPreferences.getString(USER_PASS, "")
        return if (id.isNullOrEmpty() || pass.isNullOrEmpty()) {
            //this can be handled on main screen
            Result.Failure(Exception(" Opps Some things went wrong please relogin"))
        } else {
            val request = LoginRequest(id, pass)
            api.login(request).mapperLogin(sessionManager)
        }
    }
}