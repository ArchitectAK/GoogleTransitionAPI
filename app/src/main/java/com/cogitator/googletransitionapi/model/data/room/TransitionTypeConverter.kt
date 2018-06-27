package com.cogitator.googletransitionapi.model.data.room

import android.arch.persistence.room.TypeConverter
import com.cogitator.googletransitionapi.model.models.TransitionType

/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 27/06/2018 (MM/DD/YYYY)
 */
class TransitionTypeConverter {

    @TypeConverter
    fun transitionTypeToString(type: TransitionType): String = type.name

    @TypeConverter
    fun stringToTransitionType(type: String): TransitionType = TransitionType.valueOf(type)
}