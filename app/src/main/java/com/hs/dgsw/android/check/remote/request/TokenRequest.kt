package com.hs.dgsw.android.check.remote.request

import com.google.gson.annotations.SerializedName

data class TokenRequest(
    @field:SerializedName("refreshToken")
    val refreshToken: String
)