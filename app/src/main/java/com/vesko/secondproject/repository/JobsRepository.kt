package com.vesko.secondproject.repository

import androidx.lifecycle.LiveData
import com.vesko.secondproject.model.JobDao
import com.vesko.secondproject.model.JobPojo
import com.vesko.secondproject.network.WebService
import javax.inject.Inject

class JobsRepository @Inject constructor(
    private val remoteDataSource: WebService,
    private val localDataSource: JobDao
) {

    fun getLocalJobs(): LiveData<List<JobPojo>> {
        return localDataSource.getAllJobs()
    }

    fun getLocalJobById(id: String): LiveData<JobPojo> {
        return localDataSource.getJobById(id)
    }

    suspend fun insertJobs(jobsList: List<JobPojo>) {
        localDataSource.insertAll(jobsList)
    }

    suspend fun getJobs(location: String, desc: String): List<JobPojo> {
        return remoteDataSource.getJobs(location, desc)
    }

    suspend fun deleteAllJobs() {
        localDataSource.deleteAll()
    }
}