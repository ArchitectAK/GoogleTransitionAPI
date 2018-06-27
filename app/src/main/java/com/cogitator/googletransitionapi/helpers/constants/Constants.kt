package com.cogitator.googletransitionapi.helpers.constants

import com.cogitator.googletransitionapi.BuildConfig

/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 27/06/2018 (MM/DD/YYYY)
 */
object Constants {
    /**
     * Package firstName of the app
     */
    @JvmStatic
    private val PACKAGE_NAME = BuildConfig.APPLICATION_ID

    /**
     * Default key of SharedPreferences
     */
    @JvmStatic
    val DEFAULT_SHARED_PREF_KEY = "$PACKAGE_NAME.SHARED_PREF_KEY"

    /**
     * Background service channel ID
     */
    @JvmStatic
    val TRANSITION_SERVICE_CHANNEL_ID = "$PACKAGE_NAME.TRANSITION_SERVICE_CHANNEL_ID"

    /**
     * Default channel
     */
    @JvmStatic
    val DEFAULT_CHANNEL_ID = "$PACKAGE_NAME.DEFAULT_CHANNEL_ID"

    /**
     * Notification ID for foreground service
     */
    @JvmStatic
    val FOREGROUND_SERVICE_NOTIFICATION_ID = 200
}