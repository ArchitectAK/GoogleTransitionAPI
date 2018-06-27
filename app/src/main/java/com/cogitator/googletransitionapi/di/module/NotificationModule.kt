package com.cogitator.googletransitionapi.di.module

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import com.cogitator.googletransitionapi.MainActivity
import com.cogitator.googletransitionapi.R
import com.cogitator.googletransitionapi.di.qualifier.ApplicationContext
import com.cogitator.googletransitionapi.di.qualifier.LocationNotificationChannelId
import com.cogitator.googletransitionapi.helpers.constants.RequestCodes
import dagger.Module
import dagger.Provides
import org.jetbrains.anko.intentFor
import javax.inject.Singleton

/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 27/06/2018 (MM/DD/YYYY)
 */
@Module
class NotificationModule {
    /**
     * Provide NotificationManager
     */
    @Singleton
    @Provides
    fun provideNotificationManager(@ApplicationContext context: Context): NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    /**
     * Provide channel ID for notification
     */
    @Singleton
    @Provides
    @LocationNotificationChannelId
    fun provideLocationNotificationChannelId(
            notificationChannelBuilder: NotificationChannelBuilder): String =
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                notificationChannelBuilder.createLocationChannel()
            } else {
                ""
            }

    /**
     * Provide notification for running LocationService as foreground service
     */
    @Singleton
    @Provides
    fun provideLocationNotification(
            @ApplicationContext context: Context,
            @LocationNotificationChannelId channelId: String): Notification {
        val intent = context.intentFor<MainActivity>().setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        val pendingIntent = PendingIntent.getActivity(context, RequestCodes.HOME_ACTIVITY_PI_CODE,
                intent, 0)
        return NotificationCompat.Builder(context, channelId)
                .setContentTitle(context.getString(R.string.app_name) + " is running")
                .setContentText("Touch to open.")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .build()
    }
}