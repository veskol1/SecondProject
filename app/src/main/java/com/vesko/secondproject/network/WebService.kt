package com.vesko.secondproject.network

import com.vesko.secondproject.model.JobPojo
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("/positions.json")
    suspend fun getJobs(
        @Query("location") location: String,
        @Query("description") desc: String
    ): List<JobPojo>
}