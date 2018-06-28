package com.cogitator.googletransitionapi.di.module

import com.cogitator.googletransitionapi.MainActivity
import com.cogitator.googletransitionapi.view.main.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 26/06/2018 (MM/DD/YYYY)
 */
@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun provideHomeActivity(): MainActivity
}