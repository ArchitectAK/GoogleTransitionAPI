package com.cogitator.googletransitionapi.model.data.transitions

import android.app.IntentService
import android.content.Intent
import com.cogitator.googletransitionapi.model.data.DataManager
import com.cogitator.googletransitionapi.model.models.TransitionEvent
import com.google.android.gms.location.ActivityTransitionResult
import dagger.android.AndroidInjection
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 28/06/2018 (MM/DD/YYYY)
 */
class TransitionIntentService : IntentService("TransitionIntentService") {

    @Inject
    lateinit var dataManager: DataManager

    override fun onCreate() {
        // Inject external dependencies
        AndroidInjection.inject(this)
        super.onCreate()
        Timber.i("Created.")
    }

    override fun onHandleIntent(intent: Intent?) {
        if (ActivityTransitionResult.hasResult(intent)) {
            val result = ActivityTransitionResult.extractResult(intent)
            result?.let {
                for (event in it.transitionEvents) {
                    event?.run {
                        // chronological sequence of events....
                        Timber.d(this.toString())
                        val transitionEvent = TransitionEvent(this)
                        dataManager.insertEvent(transitionEvent)
                    }
                }
            }
        }
    }
}