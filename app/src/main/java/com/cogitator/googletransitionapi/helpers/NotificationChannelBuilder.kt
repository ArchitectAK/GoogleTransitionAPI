package com.cogitator.googletransitionapi.helpers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ContextWrapper
import android.os.Build
import android.content.Context
import com.cogitator.googletransitionapi.di.qualifier.ApplicationContext
import android.support.annotation.RequiresApi
import com.cogitator.googletransitionapi.R
import com.cogitator.googletransitionapi.helpers.constants.Constants.DEFAULT_CHANNEL_ID
import com.cogitator.googletransitionapi.helpers.constants.Constants.TRANSITION_SERVICE_CHANNEL_ID
import javax.inject.Inject

/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 27/06/2018 (MM/DD/YYYY)
 */
class NotificationChannelBuilder @Inject constructor(
        @ApplicationContext val context: Context,
        val notificationManager: NotificationManager) : ContextWrapper(context) {

    @RequiresApi(api = Build.VERSION_CODES.O)
    fun createLocationChannel(): String {
        val channel = NotificationChannel(TRANSITION_SERVICE_CHANNEL_ID,
                getString(R.string.location_channel_name),
                NotificationManager.IMPORTANCE_LOW)
        createNotificationChannel(channel, TRANSITION_SERVICE_CHANNEL_ID)
        return TRANSITION_SERVICE_CHANNEL_ID
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    fun createDefaultChannel(): String {
        val channel = NotificationChannel(DEFAULT_CHANNEL_ID,
                getString(R.string.default_channel_name),
                NotificationManager.IMPORTANCE_DEFAULT)
        channel.enableVibration(true)
        createNotificationChannel(channel, DEFAULT_CHANNEL_ID)
        return DEFAULT_CHANNEL_ID
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private fun createNotificationChannel(channel: NotificationChannel, id: String) {
        if (notificationManager.getNotificationChannel(id) == null) {
            notificationManager.createNotificationChannel(channel)
        }
    }
}