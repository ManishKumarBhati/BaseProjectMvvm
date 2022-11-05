package com.bmk.data.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.bmk.data.ApiService
import com.bmk.data.data.LoginRequest
import com.bmk.data.doa.DoaService
import com.bmk.data.mapper.mapper
import com.bmk.data.mapper.mapperLogin
import com.bmk.data.network.SessionManager
import com.bmk.data.util.USER_ID
import com.bmk.data.util.USER_PASS
import com.bmk.domain.LoginDataResponse
import com.bmk.domain.Repository
import com.bmk.domain.Response
import com.bmk.domain.Result
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