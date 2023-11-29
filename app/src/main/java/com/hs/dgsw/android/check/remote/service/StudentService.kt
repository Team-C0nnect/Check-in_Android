package com.hs.dgsw.android.check.remote.service

import com.hs.dgsw.android.check.remote.request.LoginRequest
import com.hs.dgsw.android.check.remote.request.StudentRequest
import com.hs.dgsw.android.check.remote.response.StudentResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface StudentService {
//    @Headers("Authorization: CheckInDataBase.getInstance(applicationContext)?.tokenDao()?.getMembers().accessToken")
    @GET("/student")
    suspend fun getStudent(

    ):StudentResponse

    @POST("/student")
    suspend fun postStudent(
        @Body body: StudentRequest
    )
}