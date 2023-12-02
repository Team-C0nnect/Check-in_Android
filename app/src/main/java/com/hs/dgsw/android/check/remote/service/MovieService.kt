package com.hs.dgsw.android.check.remote.service

import com.hs.dgsw.android.check.remote.request.MovieApplyRequest
import com.hs.dgsw.android.check.remote.response.MovieVoteResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MovieService {
    @POST("/movie")
    suspend fun postMovieApply(
        @Body body: MovieApplyRequest
    )

    @GET("/movie/list")
    suspend fun getMovieVote(

    ):MovieVoteResponse
}