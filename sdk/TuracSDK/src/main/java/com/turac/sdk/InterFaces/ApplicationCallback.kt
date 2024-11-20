package com.turac.sdk

import com.mylibrary.sdk.turacsdk.Data.Model.Application.Application

interface ApplicationCallback {
    fun onSuccess(app: Application)
    fun onFailure(t: Throwable)
}