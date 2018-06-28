package com.cogitator.googletransitionapi.di.module

import com.cogitator.googletransitionapi.model.data.transitions.TransitionIntentService
import com.cogitator.googletransitionapi.model.data.transitions.TransitionIntentServiceModule
import com.cogitator.googletransitionapi.model.data.transitions.TransitionService
import com.cogitator.googletransitionapi.model.data.transitions.TransitionServiceModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 27/06/2018 (MM/DD/YYYY)
 */
@Module
abstract class ServiceBuilder {
    @ContributesAndroidInjector(modules = [
        TransitionServiceModule::class])
    abstract fun provideTransitionService(): TransitionService

    @ContributesAndroidInjector(modules = [
        TransitionIntentServiceModule::class])
    abstract fun provideTransitionIntentService(): TransitionIntentService
}