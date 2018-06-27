package com.cogitator.googletransitionapi.model.data.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.cogitator.googletransitionapi.model.models.TransitionEvent

/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 27/06/2018 (MM/DD/YYYY)
 */
const val APP_DB_VERSION = 1

@Database(entities = [TransitionEvent::class], version = APP_DB_VERSION)
@TypeConverters(ActivityTypeConverter::class, TransitionTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getTransitionDao(): TransitionDao
}