package com.waesh.mindfulbell.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.waesh.mindfulbell.model.repository.Repository

class ApplicationViewModel(private val repository: Repository): ViewModel() {

    private val _homeText = MutableLiveData<String>("This is Home Fragment.")
    val homeText: LiveData<String> = _homeText

}

class ApplicationViewModelFactory(private val repository: Repository): ViewModelProvider.Factory{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ApplicationViewModel::class.java)){
            return ApplicationViewModel(repository) as T
        }
        throw java.lang.IllegalArgumentException("Unknown VM class")
    }
}