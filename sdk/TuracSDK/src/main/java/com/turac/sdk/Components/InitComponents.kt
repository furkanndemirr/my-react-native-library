package com.mylibrary.turacsdk.Components
import com.mylibrary.turacsdk.Constants.PermissionStatus
import com.mylibrary.turacsdk.Constants.PermissionType
import com.mylibrary.turacsdk.Contents.DeviceInfo
import com.mylibrary.turacsdk.Contents.DeviceInfo.getDeviceDetails
import com.mylibrary.turacsdk.Contents.InitServices
import com.mylibrary.turacsdk.Contents.InitServices.checkPermissionService
import com.mylibrary.turacsdk.Contents.InitServices.deviceAddService
import com.mylibrary.turacsdk.Contents.InitServices.initService
import com.mylibrary.turacsdk.Contents.Endpoints.getAllEndpoints
import com.mylibrary.turacsdk.Data.Model.Application.Application
import com.mylibrary.turacsdk.Data.Model.Endpoint.Endpoint
import com.mylibrary.turacsdk.InterFaces.ApplicationCallback
import com.mylibrary.turacsdk.InterFaces.EndpointCallback
import com.mylibrary.turacsdk.InterFaces.PermissionCallback
import com.mylibrary.turacsdk.InterFaces.ResponseCallback
import com.mylibrary.turacsdk.helper.PrefHelper
import com.mylibrary.turacsdk.Contents.ContextInfo.getContext


object InitComponents {
    fun initDevice(appkey: String, loginPhone: String, loginEmail: String,callback : PermissionCallback) {
        PrefHelper.putString(PrefHelper.Token, appkey)
        getAllEndpoints(object : EndpointCallback {
            override fun onSuccess(endpointList: List<Endpoint>) {
                setAllEndpoints(endpointList)
                initService(appkey, object : ApplicationCallback {
                    override fun onSuccess(app: Application) {
                        if (app.isActive == true) {
                            PrefHelper.putString(PrefHelper.AppId,app.id.toString())
                            PrefHelper.putString(PrefHelper.GUID, app.guid)
                            PrefHelper.putString(PrefHelper.InitStatus, app.isActive.toString())
                            PrefHelper.putString(PrefHelper.PhoneNumber, "234566")
                            PrefHelper.putString(PrefHelper.Imei, "deneme")
                            val deviceId : String = DeviceInfo.getDeviceId()
                            if (PrefHelper.getString(PrefHelper.DeviceId) != deviceId) {
                                var device = getDeviceDetails(getContext())
                                device.loginEmail = loginEmail
                                device.loginMsisdn = loginPhone
                                deviceAddService(device, object : ResponseCallback {
                                     override fun onSuccess(result: Boolean) {
                                          if (result) {
                                              PrefHelper.putString(PrefHelper.DeviceId, deviceId)
                                              checkPermission(app.id, deviceId, callback)
                                          }
                                     }

                                    override fun onFailure(t: Throwable) {
                                        callback.onFailure(t)
                                    }

                                })

                            } else {
                                checkPermission(app.id, deviceId, callback)
                            }
                        }
                    }
                    override fun onFailure(t: Throwable) {
                        callback.onFailure(t)
                    }
                })
            }

            override fun onFailure(t: Throwable) {
                callback.onFailure(t)
            }
        })




    }

    fun checkPermission( appId: Number, deviceId:String, callback: PermissionCallback) {
        checkPermissionService(appId, deviceId, PermissionType.ticari.toString(), object :PermissionCallback{
            override fun onSuccess(permission: String) {
                if(permission != PermissionStatus.KAYIT_YOK.toString()) {
                    InitServices.setOnwTimeWorkRequest()
                }
                callback.onSuccess(permission)
            }

            override fun onFailure(t: Throwable) {
                callback.onFailure(t)
            }
        })
    }

    fun setAllEndpoints(endpointList : List<Endpoint>) {
        if (endpointList != null && endpointList.size > 0) {
            for (endpoint in endpointList) {
                PrefHelper.putString(endpoint.name, endpoint.uri)
            }
        }
    }
}