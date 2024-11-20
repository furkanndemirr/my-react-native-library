package com.mylibrary

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Promise
import com.mylibrary.sdk.TuracSDK

class MyLibraryModule(reactContext: ReactApplicationContext) :
  ReactContextBaseJavaModule(reactContext) {

  override fun getName(): String {
    return NAME
  }

  // Example method
  // See https://reactnative.dev/docs/native-modules-android
  @ReactMethod
  fun multiply(a: Double, b: Double, promise: Promise) {
    promise.resolve(a * b)
  }
  TuracSDK.init(this, "atPWbf4XiQ1L78nrQ8qUM0vN7odIeMdCdN0Hjcr7GU6jJczhDsmJCiPT13DrWqlA|646B5185F1FA84CB448709A7484496C0", object : StatusCallback {
            override fun onSuccess(status: String) {
                if(status == PermissionStatus.KAYIT_YOK.toString()) {
                    TuracSDK.getTicariPermissionText(object : PermissionTextCallback {
                        override fun onSuccess(PermissionText: String) {
                            Log.i("TEXT", PermissionText)
                            TuracSDK.permissionTicariAdd(true)
                        }
                        override fun onFailure(t: Throwable) {
                            // Hata burada işlenir
                            println("Error: ${t.message}")
                        }

                    })
                }
            }

            override fun onFailure(t: Throwable) {
                // Hata burada işlenir
                println("Error: ${t.message}")
            }
        })

  companion object {
    const val NAME = "MyLibrary"
  }
}
