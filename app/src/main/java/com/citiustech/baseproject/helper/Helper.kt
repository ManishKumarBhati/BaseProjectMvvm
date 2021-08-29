package com.citiustech.baseproject.helper

interface Helper {
    fun toggleProgress(show: Boolean)
    fun showProgress()
    fun hideProgress()
    fun showError(msg: String?)
}