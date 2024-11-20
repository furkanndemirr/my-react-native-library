package com.turac.sdk


import com.mylibrary.turacsdk.Data.Model.Endpoint.Endpoint

interface EndpointCallback {
    fun onSuccess(endpointList: List<Endpoint>)
    fun onFailure(t: Throwable)
}