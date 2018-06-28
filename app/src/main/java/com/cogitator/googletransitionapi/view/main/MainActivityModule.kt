package com.cogitator.googletransitionapi.view.main

import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.cogitator.googletransitionapi.MainActivity
import com.cogitator.googletransitionapi.di.qualifier.ApplicationContext
import com.cogitator.googletransitionapi.helpers.utils.intentFor
import com.cogitator.googletransitionapi.model.data.transitions.TransitionService
import com.cogitator.googletransitionapi.view.main.adapter.TransitionsAdapter
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 28/06/2018 (MM/DD/YYYY)
 */
@Module
class MainActivityModule {

    /**
     * Provide intent to LocationService
     */
    @Provides
    fun provideTransitionServiceIntent(@ApplicationContext context: Context): Intent =
            context.intentFor<TransitionService>()


    @Provides
    fun provideCompositeDisposable() = CompositeDisposable()

    @Provides
    fun provideLinearLayoutManager(homeActivity: MainActivity) = LinearLayoutManager(homeActivity)

    @Provides
    fun provideTransitionsAdapter() = TransitionsAdapter()
}