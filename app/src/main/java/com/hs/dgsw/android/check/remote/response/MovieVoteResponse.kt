package com.hs.dgsw.android.check.remote.response

import android.icu.text.CaseMap.Title
import com.google.gson.annotations.SerializedName

data class MovieVoteResponse (
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("userId")
    val stdId: Int,
    @field:SerializedName("title")
    val title: String,
    @field:SerializedName("createdDateTime")
    val createdDateTime: String
)


