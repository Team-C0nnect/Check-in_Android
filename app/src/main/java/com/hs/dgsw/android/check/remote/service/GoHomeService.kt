package com.hs.dgsw.android.check.remote.service

import com.hs.dgsw.android.check.remote.request.GoHomeRequest
import com.hs.dgsw.android.check.remote.response.GoHomeResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface GoHomeService {
    @POST("/sleepover/")
    suspend fun postGoHome(
        @Body body: GoHomeRequest
    )
}