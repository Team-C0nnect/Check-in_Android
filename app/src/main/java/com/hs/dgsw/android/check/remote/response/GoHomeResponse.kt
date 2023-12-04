package com.hs.dgsw.android.check.remote.response

import com.google.gson.annotations.SerializedName

data class GoHomeResponse(
    @field:SerializedName("userId")
    val userId: Int,
    @field:SerializedName("reason")
    val reason: String,
    @field:SerializedName("approval")
    val approval: String,
    @field:SerializedName("startDateTime")
    val startDateTime: String,
    @field:SerializedName("endDateTime")
    val endDateTime: String


)