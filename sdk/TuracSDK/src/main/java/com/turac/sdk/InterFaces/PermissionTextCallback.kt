package com.turac.sdk


interface PermissionTextCallback {
    fun onSuccess(permissionText: String)
    fun onFailure(t: Throwable)
}