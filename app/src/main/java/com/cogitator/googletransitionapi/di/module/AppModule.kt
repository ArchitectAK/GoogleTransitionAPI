package com.cogitator.googletransitionapi.di.module

import android.app.Application
import android.content.Context
import com.cogitator.googletransitionapi.di.qualifier.ApplicationContext
import dagger.Module
import dagger.Provides

/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 26/06/2018 (MM/DD/YYYY)
 */


@Module(subcomponents = [] /* Add additional sub components here */)
class AppModule {
    /**
     * Provide app context
     */
    @Provides
    @ApplicationContext
    fun provideAppContext(app: Application): Context = app
}