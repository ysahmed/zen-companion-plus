package com.waesh.mindfulbell

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import com.waesh.mindfulbell.application.MindfulApplication
import java.util.Calendar

class Receiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (Intent.ACTION_BOOT_COMPLETED == intent?.action ||
            Intent.ACTION_LOCKED_BOOT_COMPLETED == intent?.action ||
            Intent.ACTION_REBOOT == intent?.action
            ){
            val application = context?.applicationContext as MindfulApplication
            application.startTimer()
            application.endTimer()
        }
    }

}