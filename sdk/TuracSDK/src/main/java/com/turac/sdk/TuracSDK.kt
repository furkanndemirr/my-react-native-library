package com.mylibrary.sdk

import android.content.Context
import com.mylibrary.sdk.Components.InitComponents
import com.mylibrary.sdk.Components.PermissionComponents
import com.mylibrary.sdk.Contents.ContextInfo
import com.mylibrary.sdk.InterFaces.PermissionCallback
import com.mylibrary.sdk.InterFaces.PermissionTextCallback
import com.mylibrary.sdk.InterFaces.StatusCallback
import com.mylibrary.sdk.helper.PrefHelper

object TuracSDK {
    fun init(context: Context, appKey: String, callback: StatusCallback, loginPhone: String = "", loginEmail: String = "") {
        ContextInfo.setContext(context.applicationContext)
        PrefHelper.init(context.applicationContext)
        InitComponents.initDevice(appKey, loginPhone, loginEmail, object : PermissionCallback {
            override fun onSuccess(permission: String) {
                callback.onSuccess(permission)
            }

            override fun onFailure(t: Throwable) {
                callback.onFailure(t)
            }
        })
    }

    fun getTicariPermissionText(callback: PermissionTextCallback) {
        PermissionComponents.getTicariPermissionText( object : PermissionTextCallback {
            override fun onSuccess(permissionText: String) {
                callback.onSuccess(permissionText)
            }

            override fun onFailure(t: Throwable) {
                callback.onFailure(t)
            }
        })
    }

    fun permissionTicariAdd(permissionStatus: Boolean) {
        PermissionComponents.permissionTicariAdd(permissionStatus)
    }
}