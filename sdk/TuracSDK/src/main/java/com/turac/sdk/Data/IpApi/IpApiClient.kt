package com.mylibrary.turacsdk.Data.IpApi
import com.google.gson.GsonBuilder
import com.turkcell.turacsdk.Data.Api.IpApiCall
import com.turkcell.turacsdk.InterFaces.PublicIpCallback
import retrofit2.Retrofit
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory


class IpResponse {
    val ip: String? = null
}

object IPApiClient {
    fun fetchPublicIp(callback: PublicIpCallback) {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://ipinfo.io/")
            .addConverterFactory(GsonConverterFactory.create(gson)) // Use ScalarsConverterFactory for plain text
            .build()

        val ipService = retrofit.create(IpApiCall::class.java)
        val call = ipService.getPublicIp()
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    val ip = response.body() ?: "" // The IP is returned as a plain string
                    if (ip.isNotEmpty()) {
                        callback.onSuccess(ip)
                    } else {
                        callback.onFailure(Throwable("ip is empty"))
                    }
                } else {
                    callback.onFailure(Throwable("response is not succesful"))
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                callback.onFailure(t)
            }
        })
    }
}