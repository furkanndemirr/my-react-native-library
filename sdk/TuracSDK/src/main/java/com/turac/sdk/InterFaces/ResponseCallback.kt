package com.turac.sdk


interface ResponseCallback {
    fun onSuccess(app: Boolean)
    fun onFailure(t: Throwable)
}