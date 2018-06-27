package com.cogitator.googletransitionapi.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 27/06/2018 (MM/DD/YYYY)
 */
@Module
class ServiceBuilder {
    @ContributesAndroidInjector(modules = [
        TransitionServiceModule::class])
    abstract fun provideTransitionService(): TransitionService

    @ContributesAndroidInjector(modules = [
        TransitionIntentServiceModule::class])
    abstract fun provideTransitionIntentService(): TransitionIntentService
}