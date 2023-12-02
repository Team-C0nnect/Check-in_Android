package com.hs.dgsw.android.check.remote.request

import com.google.gson.annotations.SerializedName

data class MovieApplyRequest (
    @field:SerializedName("title")
    val title: String
)
