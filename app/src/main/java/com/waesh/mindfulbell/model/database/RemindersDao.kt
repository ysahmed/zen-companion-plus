package com.waesh.mindfulbell.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.waesh.mindfulbell.model.entity.Reminder
import kotlinx.coroutines.flow.Flow

@Dao
interface RemindersDao {
    @Insert
    suspend fun addReminder(item: Reminder)

    @Query("UPDATE reminders SET enabled = :enabled WHERE id = :id")
    fun updateEnabledById(id: Int, enabled: Boolean)

    @Query("UPDATE reminders SET favorite = :favorite WHERE id = :id")
    fun updateFavoriteById(id: Int, favorite: Boolean)

    @Query("SELECT * FROM reminders")
    fun getAll(): Flow<List<Reminder>>
}