package com.waesh.mindfulbell.viewmodel

import androidx.lifecycle.*
import com.waesh.mindfulbell.model.entity.Reminder
import com.waesh.mindfulbell.model.repository.Repository

class RemindersViewModel(private val repository: Repository): ViewModel() {

    private val selections: MutableList<Reminder> = mutableListOf()

    val reminders = repository.reminders.asLiveData()

    fun addSelection(reminder: Reminder) = selections.add(reminder)

    fun removeSelection(reminder: Reminder) = selections.remove(reminder)

    fun updateEnabledById(id: Int, enabled: Boolean) = repository.updateEnabledById(id, enabled)

    fun updateFavoriteById(id: Int, favorite: Boolean) = repository.updateFavoriteById(id, favorite)

}

class RemindersViewModelFactory(private val repository: Repository): ViewModelProvider.Factory{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RemindersViewModel::class.java)){
            return RemindersViewModel(repository) as T
        }
        throw java.lang.IllegalArgumentException("Unknown VM class. RemindersViewModel expected.")
    }
}