package com.vesko.secondproject.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class JobPojo(
    @PrimaryKey
    @ColumnInfo(name = "job_id")
    val id: String,

    @ColumnInfo(name = "job_company")
    val company: String,

    @ColumnInfo(name = "job_title")
    val title: String,

    @ColumnInfo(name = "job_description")
    val description: String,

    @ColumnInfo(name = "job_company_logo")
    val company_logo: String?)
{



}