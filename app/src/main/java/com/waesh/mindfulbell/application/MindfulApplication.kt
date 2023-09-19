package com.waesh.mindfulbell.application

import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.waesh.mindfulbell.ACTION_RING_BELL_INTENT
import com.waesh.mindfulbell.PREF
import com.waesh.mindfulbell.PREF_ENABLED
import com.waesh.mindfulbell.PREF_END_HOUR
import com.waesh.mindfulbell.PREF_END_MINUTE
import com.waesh.mindfulbell.PREF_INTERVAL
import com.waesh.mindfulbell.PREF_START_HOUR
import com.waesh.mindfulbell.PREF_START_MINUTE
import com.waesh.mindfulbell.model.database.Database
import com.waesh.mindfulbell.model.repository.Repository
import java.util.Calendar

class MindfulApplication: Application() {

    private val database: Database by lazy {
        Database.getDatabase(this@MindfulApplication)
    }
    val repository: Repository by lazy {
        Repository(database.getDao())
    }

    private lateinit var ringBellIntent: PendingIntent
    val _alarmIntent: PendingIntent
        get() = ringBellIntent

    fun startTimer(){
        // check if enabled
        // check if exists
        /* boolean alarmUp = (PendingIntent.getBroadcast(context, 0,
        new Intent("com.my.package.MY_UNIQUE_ACTION"),
        PendingIntent.FLAG_NO_CREATE) != null); */
        // need to play sound and show notification
        ringBellIntent = Intent(ACTION_RING_BELL_INTENT).let { intent ->
            PendingIntent.getBroadcast(this, 123458, intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val pref = getSharedPreferences(PREF, Context.MODE_PRIVATE)

        if (isAlarmSet(this, ringBellIntent) && pref.getBoolean(PREF_ENABLED, false)){
            // then set alarm
            val calendar: Calendar = Calendar.getInstance().apply {
                timeInMillis = System.currentTimeMillis()
                set(Calendar.HOUR_OF_DAY, pref.getInt(PREF_START_HOUR, 8))    //from pref start hour
                set(Calendar.MINUTE, pref.getInt(PREF_START_MINUTE, 30))    //from pref start minute
            }

            (getSystemService(ALARM_SERVICE) as? AlarmManager)?.setRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                pref.getLong(PREF_INTERVAL, 60*60*1000), // from pref interval
                ringBellIntent
            )
        }
    }

    fun endTimer(){
        // check if enabled
        // check if exists
        val pref = getSharedPreferences(PREF, Context.MODE_PRIVATE)

        if (isAlarmSet(this, ringBellIntent) && pref.getBoolean(PREF_ENABLED, false)){
            // then set alarm
            val calendar: Calendar = Calendar.getInstance().apply {
                timeInMillis = System.currentTimeMillis()
                set(Calendar.HOUR_OF_DAY, pref.getInt(PREF_END_HOUR, 23))    //from pref end hour
                set(Calendar.MINUTE, pref.getInt(PREF_END_MINUTE, 0))    //from pref end minute
            }

            (getSystemService(ALARM_SERVICE) as? AlarmManager)?.setAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                ringBellIntent
            )
        }
    }

    private fun isAlarmSet(context: Context, pendingIntent: PendingIntent): Boolean {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val nextAlarmClock = alarmManager.nextAlarmClock

        return nextAlarmClock != null && nextAlarmClock.showIntent == pendingIntent
    }
}