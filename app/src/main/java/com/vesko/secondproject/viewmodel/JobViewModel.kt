package com.vesko.secondproject.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.vesko.secondproject.model.JobPojo
import com.vesko.secondproject.repository.JobsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class JobViewModel @Inject constructor(private val repository: JobsRepository): ViewModel(){

    private val _id = MutableLiveData<String>()

    private val _job = _id.switchMap { id ->
        repository.getLocalJobById(id)
    }

    val job: LiveData<JobPojo> = _job

    fun start(id: String) {
        _id.value = id

    }
}