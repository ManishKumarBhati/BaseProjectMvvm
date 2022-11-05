package com.bmk.domain

interface SessionHelper {
    fun isRefreshRequired(): Boolean
    fun saveToken()
}