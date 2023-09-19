package com.waesh.mindfulbell.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reminders")
data class Reminder(
    var body: String,
    var enabled: Boolean = false,
    var favorite: Boolean = false
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
