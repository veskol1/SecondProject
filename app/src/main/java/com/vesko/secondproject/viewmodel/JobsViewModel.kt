package com.vesko.secondproject.viewmodel

import androidx.lifecycle.*
import com.vesko.secondproject.model.JobPojo
import com.vesko.secondproject.repository.JobsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class JobsViewModel @Inject constructor(private val repository: JobsRepository) : ViewModel() {

    val myJobs = MutableLiveData<List<JobPojo>>()
    val localJobs : LiveData<List<JobPojo>> = repository.getLocalJobs()

    fun fetchJobs(location: String, desc: String){
        viewModelScope.launch{
            val dataJob = repository.getJobs(location, desc)
            myJobs.value = dataJob

            repository.deleteAllJobs()
            repository.insertJobs(dataJob)
        }
    }
}