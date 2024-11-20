package com.turac.sdk

import com.mylibrary.turacsdk.Data.Model.Permission.*

interface PermissionCallback {
    fun onSuccess(app: String)
    fun onFailure(t: Throwable)
}