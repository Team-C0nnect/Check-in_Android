package com.hs.dgsw.android.check.remote.request

import com.google.gson.annotations.SerializedName


// 요구
data class LoginRequest(
    @field:SerializedName("idToken")
    val idToken: String,
    @field:SerializedName("fcmToken")
    val fcmToken: String
)
