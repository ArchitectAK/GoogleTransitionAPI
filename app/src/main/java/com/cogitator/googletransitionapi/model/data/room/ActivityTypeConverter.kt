package com.cogitator.googletransitionapi.model.data.room

import android.arch.persistence.room.TypeConverter
import com.cogitator.googletransitionapi.model.models.ActivityType

/**
 *
 *  TypeConverter class for Room to allow {@link ActivityType} enum
 * to be converted to string and vice versa
 *
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 27/06/2018 (MM/DD/YYYY)
 */
class ActivityTypeConverter {

    @TypeConverter
    fun activityTypeToString(type: ActivityType): String = type.name

    @TypeConverter
    fun stringToActivityType(type: String): ActivityType = ActivityType.valueOf(type)
}