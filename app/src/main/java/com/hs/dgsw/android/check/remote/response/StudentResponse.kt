package com.hs.dgsw.android.check.remote.response

import com.google.gson.annotations.SerializedName

data class StudentResponse (
    @field:SerializedName("id")
    val id: String,
    @field:SerializedName("stdId")
    val stdId: String
)