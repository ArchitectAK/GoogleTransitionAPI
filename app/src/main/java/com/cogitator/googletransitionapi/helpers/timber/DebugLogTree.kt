package com.cogitator.googletransitionapi.helpers.timber

import android.os.Build
import android.util.Log
import timber.log.Timber

/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 27/06/2018 (MM/DD/YYYY)
 */
class DebugLogTree : Timber.DebugTree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        var logPriority = priority
        // Workaround vor Huawei devices not showing logs
        if (Build.MANUFACTURER == "HUAWEI" || Build.MANUFACTURER == "samsung") {
            if (logPriority == Log.VERBOSE || logPriority == Log.DEBUG || logPriority == Log.INFO)
                logPriority = Log.ERROR
        }
        super.log(logPriority, tag, message, t)
    }

    override fun createStackElementTag(element: StackTraceElement): String {
        // Add line numbers to log
        return super.createStackElementTag(element) + " - " + element.lineNumber
    }
}