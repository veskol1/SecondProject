package com.vesko.secondproject.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface JobDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(jobs: List<JobPojo>)

    @Query("SELECT  * FROM JobPojo")
    fun getAllJobs() : LiveData<List<JobPojo>>

    @Query("SELECT * FROM JobPojo WHERE job_id = :id")
    fun getJobById(id: String): LiveData<JobPojo>

    @Query("DELETE FROM JobPojo")
    suspend fun deleteAll()

}