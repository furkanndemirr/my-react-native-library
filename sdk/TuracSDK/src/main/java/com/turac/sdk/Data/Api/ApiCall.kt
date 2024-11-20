package com.mylibrary.turacsdk.Data.Api
import com.mylibrary.turacsdk.Data.Model.Application.Application
import com.mylibrary.turacsdk.Data.Model.Device.Device
import com.mylibrary.turacsdk.Data.Model.Endpoint.Endpoint
import com.mylibrary.turacsdk.Data.Model.Permission.Permission
import com.mylibrary.turacsdk.helper.PrefHelper
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiCall {
    @GET("Settings/GetAllEndpoints")
    fun getAllEndpoints(@Header("Authorization") token: String,): Call<List<Endpoint>>

    @GET
    fun getApplication(@Header("Authorization") token: String, @Url url: String, @Query("appKey") appkey: String): Call<Application>

    @GET
    fun getPermissionText(@Header("Authorization") token: String, @Url url: String, @Query("permissionType") permissionType: String): Call<String>

    @GET
    fun getPermissionStatus(@Header("Authorization") token: String, @Url url: String, @Query("appId") appId: Int, @Query("deviceId") deviceId: String, @Query("permissionType") permissionType: String): Call<String>

    @POST
    fun permissionAdd(@Header("Authorization") token: String, @Url url: String, @Body permission: Permission): Call<Boolean>

    @POST
    fun deviceAdd(@Header("Authorization") token: String, @Url url: String, @Body device: Device): Call<Boolean>

}