package com.bmk.baseproject.util


data class Data<T>(var responseType: Status, var data: T? = null, var error: Exception? = null)

enum class Status { SUCCESSFUL, ERROR, LOADING }
