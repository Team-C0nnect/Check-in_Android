package com.hs.dgsw.android.check.remote.response

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @field:SerializedName("accessToken")
    val accessToken: String,
)