package com.citiustech.baseproject.data

import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {
    @GET("todos/1")
    fun getData(): Observable<DataResponse>
}

const val Base_Url = "https://jsonplaceholder.typicode.com/"