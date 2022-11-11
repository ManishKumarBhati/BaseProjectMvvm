package com.bmk.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.bmk.data.ApiService
import com.bmk.data.doa.DoaService
import com.bmk.data.mapper.mapper
import com.bmk.domain.Repository
import com.bmk.domain.Result
import com.bmk.domain.UserDetails
import javax.inject.Inject


class RepositoryImpl @Inject constructor(
    val api: ApiService,
    val doaService: DoaService
) :
    Repository {
    override suspend fun getData(id: String): Result<List<UserDetails>> {
        return api.getData(id).mapper(doaService)
    }

    override fun getUser(id: String): LiveData<Result<UserDetails>> {
        return Transformations.map(doaService.getUserDetails()) {

            if (it == null) Result.Failure(Exception())
            else Result.Success(
                UserDetails(
                    id = it.id,
                    email = it.email,
                    lastName = it.lastName,
                    firstName = it.firstName,
                    avatar = it.avatar
                )
            )
        }
    }

    override fun getAllUserDetails(): LiveData<List<UserDetails>> {
        return Transformations.map(doaService.getAllUserDetails()) { response ->
            response?.map {
                UserDetails(
                    id = it.id,
                    email = it.email,
                    lastName = it.lastName,
                    firstName = it.firstName,
                    avatar = it.avatar
                )
            }
        }

    }

    override suspend fun update(id: Int, name: String) {
        doaService.update(id, name)
    }

    override suspend fun delete(id: Int) {
        doaService.delete(id)
    }
}