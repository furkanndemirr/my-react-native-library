package com.mylibrary.turacsdk.Components
import com.mylibrary.turacsdk.Constants.PermissionType
import com.mylibrary.turacsdk.Contents.InitServices
import com.mylibrary.turacsdk.Contents.InitServices.permissionAdd
import com.mylibrary.turacsdk.InterFaces.PermissionTextCallback
import com.mylibrary.turacsdk.InterFaces.ResponseCallback
import com.mylibrary.turacsdk.helper.PrefHelper


object PermissionComponents {

    fun permissionTicariAdd(status: Boolean) {
        val appId : Number =PrefHelper.getString(PrefHelper.AppId).toString().toInt()
        val deviceId : String =PrefHelper.getString(PrefHelper.DeviceId).toString()
        permissionAdd(appId,deviceId, PermissionType.ticari.toString(), status, object :ResponseCallback{
            override fun onSuccess(response: Boolean) {
                if (response) {
                    InitServices.setOnwTimeWorkRequest()
                }
            }

            override fun onFailure(t: Throwable) {

            }
        })
    }

    fun getTicariPermissionText(callback: PermissionTextCallback) {
        InitServices.getPermissionText( PermissionType.ticari.toString(), object :
            PermissionTextCallback {
            override fun onSuccess(permissionText: String) {
                callback.onSuccess(permissionText)
            }

            override fun onFailure(t: Throwable) {
                callback.onFailure(t)
            }
        })
    }
}