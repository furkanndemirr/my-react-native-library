package com.turac.sdk


interface PublicIpCallback {
    fun onSuccess(publicIp: String)
    fun onFailure(t: Throwable)
}