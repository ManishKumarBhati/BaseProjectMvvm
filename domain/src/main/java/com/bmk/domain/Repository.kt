package com.bmk.domain

import android.os.Parcelable
import androidx.lifecycle.LiveData
import kotlinx.android.parcel.Parcelize

interface Repository {
    suspend fun getData(id: String): Result<List<UserDetails>>
    fun getUser(id: String): LiveData<Result<UserDetails>>
    fun getAllUserDetails(): LiveData<List<UserDetails>>
    suspend fun update(id: Int, name: String)
    suspend fun delete(id: Int)

}

@Parcelize
data class UserDetails(
    @JvmField val id: Int,
    @JvmField val email: String,
    @JvmField val firstName: String,
    @JvmField val lastName: String,
    @JvmField val avatar: String
): Parcelable{
    fun getFullName()="$firstName $lastName"
}