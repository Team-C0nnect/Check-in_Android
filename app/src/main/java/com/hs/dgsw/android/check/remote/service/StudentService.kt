package com.hs.dgsw.android.check.remote.service

import com.hs.dgsw.android.check.remote.response.StudentResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface StudentService {
//    @Headers("Authorization: CheckInDataBase.getInstance(applicationContext)?.tokenDao()?.getMembers().accessToken")
    @GET("/student")
    fun getStudent(

    ): StudentResponse
}