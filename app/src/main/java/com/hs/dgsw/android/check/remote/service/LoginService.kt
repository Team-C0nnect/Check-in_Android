package com.hs.dgsw.android.check.remote.service

import com.hs.dgsw.android.check.remote.request.LoginRequest
import com.hs.dgsw.android.check.remote.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface LoginService {

    @POST("/auth")
    suspend fun login(
        @Body body: LoginRequest
    ): LoginResponse



}