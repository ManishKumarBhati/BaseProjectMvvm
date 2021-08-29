package com.citiustech.domain

interface SessionHelper {
    fun isRefreshRequired(): Boolean
    fun saveToken()
}