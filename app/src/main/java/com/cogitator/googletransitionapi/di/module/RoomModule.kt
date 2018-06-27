package com.cogitator.googletransitionapi.di.module

import android.arch.persistence.room.Room
import android.content.Context
import com.cogitator.googletransitionapi.di.qualifier.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 27/06/2018 (MM/DD/YYYY)
 */
@Module
class RoomModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "Transitions.db").build()

    @Singleton
    @Provides
    fun provideDriverDao(appDatabase: AppDatabase): TransitionDao = appDatabase.getTransitionDao()
}