package com.citiustech.domain

import io.reactivex.Observable

interface Repository {
    fun getData(): Observable<Response>
}

data class Response(
    @JvmField val id: Int,
    @JvmField val completed: Boolean,
    @JvmField val title: String,
    @JvmField val userId: Int
)