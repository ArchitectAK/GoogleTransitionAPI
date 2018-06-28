package com.cogitator.googletransitionapi.model.data.transitions

import android.app.PendingIntent
import android.content.Context
import com.cogitator.googletransitionapi.di.qualifier.ApplicationContext
import com.cogitator.googletransitionapi.helpers.constants.RequestCodes
import com.cogitator.googletransitionapi.helpers.utils.intentFor
import com.google.android.gms.location.ActivityTransition
import com.google.android.gms.location.ActivityTransitionRequest
import com.google.android.gms.location.DetectedActivity
import dagger.Module
import dagger.Provides

/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 28/06/2018 (MM/DD/YYYY)
 */
@Module
class TransitionServiceModule {

    /**
     * Provide transitions request
     */
    @Provides
    fun provideTransitionsRequest() : ActivityTransitionRequest {
        val activities = listOf(DetectedActivity.IN_VEHICLE,
                DetectedActivity.ON_BICYCLE,
                DetectedActivity.WALKING,
                DetectedActivity.RUNNING,
                DetectedActivity.STILL)
        val transitions = mutableListOf<ActivityTransition>()
        activities.forEach { activity ->
            val enterTransition = ActivityTransition.Builder()
                    .setActivityType(activity)
                    .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_ENTER)
                    .build()

            val exitTransition = ActivityTransition.Builder()
                    .setActivityType(activity)
                    .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_EXIT)
                    .build()

            transitions.add(enterTransition)
            transitions.add(exitTransition)
        }
        return ActivityTransitionRequest(transitions)
    }

    /**
     * Callback for Transition events
     */
    @Provides
    fun provideTransitionPendingIntent(@ApplicationContext context: Context): PendingIntent {
        val intent = context.intentFor<TransitionIntentService>()
        return PendingIntent.getService(context, RequestCodes.TRANSITION_INTENT_SERVICE_PI_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }
}