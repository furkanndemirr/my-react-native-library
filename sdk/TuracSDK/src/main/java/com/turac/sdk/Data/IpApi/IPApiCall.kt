package com.mylibrary.turacsdk.Data.IpApi
import retrofit2.Call
import retrofit2.http.GET


interface IpApiCall {
    @GET("ip")
    fun getPublicIp(): Call<String>

}