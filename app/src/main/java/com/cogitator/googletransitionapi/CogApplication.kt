package com.cogitator.googletransitionapi

import android.app.Activity
import android.app.Application
import android.app.Service
import com.cogitator.googletransitionapi.di.component.DaggerAppComponent
import com.cogitator.googletransitionapi.helpers.timber.DebugLogTree
import com.cogitator.googletransitionapi.helpers.timber.FileLoggingTree
import com.cogitator.googletransitionapi.helpers.timber.ReleaseLogTree
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasServiceInjector
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 26/06/2018 (MM/DD/YYYY)
 */
class CogApplication : Application(), HasActivityInjector, HasServiceInjector {
    @Inject
    lateinit var serviceInjector: DispatchingAndroidInjector<Service>

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInjector
    }

    override fun serviceInjector(): AndroidInjector<Service> {
        return serviceInjector
    }

    override fun onCreate() {
        super.onCreate()

        // Inject Dagger
        injectDagger()

        // Plant Timber
        plantTimber()
    }

    /**
     * Helper method to inject DaggerAppComponent
     */
    private fun injectDagger() {
        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this)
    }

    /**
     * Helper method to plan Timber logging trees.
     *
     * If app is in debug mode, then plant {@link DebugLogTree} & {@link FileLoggingTree},
     * else plant {@link ReleaseLogTree}
     */
    private fun plantTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugLogTree())
            val fileLoggingPlanted = Timber.forest().any { it is FileLoggingTree }
            if (!fileLoggingPlanted) Timber.plant(FileLoggingTree(this))
        } else {
            Timber.plant(ReleaseLogTree())
        }
    }
}