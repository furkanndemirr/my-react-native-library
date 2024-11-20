package com.turac.sdk


interface StatusCallback {
    fun onSuccess(status: String)
    fun onFailure(t: Throwable)
}