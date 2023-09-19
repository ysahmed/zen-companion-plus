package com.waesh.mindfulbell.model.repository

import com.waesh.mindfulbell.model.database.RemindersDao
import com.waesh.mindfulbell.model.entity.Reminder
import kotlinx.coroutines.flow.Flow

class Repository(private val dao: RemindersDao) {

    val reminders: Flow<List<Reminder>> = dao.getAll()

    fun updateEnabledById(id: Int, enabled: Boolean) = dao.updateEnabledById(id, enabled)

    fun updateFavoriteById(id: Int, favorite: Boolean) = dao.updateFavoriteById(id, favorite)

}