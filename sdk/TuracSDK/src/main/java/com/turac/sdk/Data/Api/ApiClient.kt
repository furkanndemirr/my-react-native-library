package com.mylibrary.turacsdk.Data.Api
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mylibrary.turacsdk.Constants.Constant
import com.mylibrary.turacsdk.Data.Model.Device.Device
import com.mylibrary.turacsdk.Data.Model.Permission.Permission
import com.mylibrary.turacsdk.helper.PrefHelper
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiClient {

    fun create(): ApiCall {
        var gson: Gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .baseUrl(Constant.baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiCall::class.java)
    }
    private val api = create()

    val token = PrefHelper.getString(PrefHelper.Token) ?: ""

    fun getAllEndpoints() =
        api.getAllEndpoints(token)

    fun getApplication(url: String, appkey: String ) =
        api.getApplication(token, url, appkey)

    fun getPermissionText(url: String, permissionType: String ) =
        api.getPermissionText(token, url, permissionType)

    fun getPermissionStatus(url: String, appId: Number, deviceId: String, permissionType: String ) =
        api.getPermissionStatus(token, url, appId.toInt(), deviceId, permissionType)

    fun permissionAdd(url: String, permission: Permission ) =
        api.permissionAdd(token, url, permission)

    fun deviceAdd(url: String, device: Device) =
        api.deviceAdd(token, url, device)
}