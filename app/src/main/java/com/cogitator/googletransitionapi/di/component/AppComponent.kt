package com.cogitator.googletransitionapi.di.component

import android.app.Application
import com.cogitator.googletransitionapi.CogApplication
import com.cogitator.googletransitionapi.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 26/06/2018 (MM/DD/YYYY)
 */

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ActivityBuilder::class,
    ServiceBuilder::class,
    RoomModule::class,
    NotificationModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: CogApplication)
}