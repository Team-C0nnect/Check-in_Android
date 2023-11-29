package com.hs.dgsw.android.check.remote.request

import com.google.gson.annotations.SerializedName

data class StudentRequest (
    @field:SerializedName("stdId")
    val stdId: String
)

