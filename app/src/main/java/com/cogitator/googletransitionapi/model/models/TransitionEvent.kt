package com.cogitator.googletransitionapi.model.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import com.cogitator.googletransitionapi.helpers.utils.activityTypeFromInt
import com.cogitator.googletransitionapi.helpers.utils.transitionTypeFromInt
import com.google.android.gms.location.ActivityTransitionEvent
import kotlinx.android.parcel.Parcelize

/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 27/06/2018 (MM/DD/YYYY)
 */
@Parcelize
@Entity(tableName = "transitions",
        // Index id fields and force them to be unique
        indices = [(Index(value = ["id"], unique = true))])
data class TransitionEvent(
        @PrimaryKey(autoGenerate = true)
        var id: Long? = null,
        val activityType: ActivityType,
        val transitionType: TransitionType,
        val elapsedRealTimeNanos: Long,
        val eventTimeMillis: Long = System.currentTimeMillis()) : Parcelable {

    @Ignore
    constructor(activity: ActivityTransitionEvent) : this(
            activityType = activityTypeFromInt(activity.activityType),
            transitionType = transitionTypeFromInt(activity.transitionType),
            elapsedRealTimeNanos = activity.elapsedRealTimeNanos)
}