package com.bmk.baseproject.helper

interface Helper {
    fun toggleProgress(show: Boolean)
    fun showProgress()
    fun hideProgress()
    fun showError(throwable: Throwable)
}