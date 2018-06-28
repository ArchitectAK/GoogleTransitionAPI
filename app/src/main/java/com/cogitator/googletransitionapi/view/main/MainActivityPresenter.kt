package com.cogitator.googletransitionapi.view.main

import android.content.Context
import android.support.design.widget.FloatingActionButton
import android.support.graphics.drawable.AnimatedVectorDrawableCompat
import com.cogitator.googletransitionapi.R
import com.cogitator.googletransitionapi.helpers.exceptions.DbFetchException
import com.cogitator.googletransitionapi.helpers.exceptions.NoDataFoundException
import com.cogitator.googletransitionapi.model.data.DataManager
import com.cogitator.googletransitionapi.model.data.transitions.TransitionService
import com.cogitator.googletransitionapi.view.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 28/06/2018 (MM/DD/YYYY)
 */
class MainActivityPresenter @Inject constructor(private val dataManager: DataManager) :
        BasePresenter<MainActivityView>() {

    fun loadData(disposable: Disposable) {
        mvpView?.showProgress()
        (disposable as CompositeDisposable)
                .add(dataManager.getAllEventsReversed()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { transitions ->
                                    Timber.e("Result: $transitions")
                                    mvpView?.hideProgress()

                                    if (transitions == null || transitions.isEmpty()) {
                                        mvpView?.showErrorSnack(NoDataFoundException())
                                    } else {
                                        mvpView?.showResult(transitions)
                                    }
                                },
                                { error ->
                                    Timber.e(error, "Error getting repos:")
                                    mvpView?.hideProgress()
                                    mvpView?.showErrorSnack(DbFetchException())
                                }
                        )
                )
    }

    fun toggleTransitionUpdate() {
        if (TransitionService.isInProgress) mvpView?.stopTransitionService()
        else mvpView?.startTransitionService()
    }

    fun prepareFab(context: Context, fab: FloatingActionButton) {
        val avd = if (TransitionService.isInProgress) {
            AnimatedVectorDrawableCompat.create(context, R.drawable.stop_to_play_anim)
        } else {
            AnimatedVectorDrawableCompat.create(context, R.drawable.play_to_stop_anim)
        }
        fab.setImageDrawable(avd)
    }

    fun animateFab(context: Context, fab: FloatingActionButton) {
        val avd = if (TransitionService.isInProgress) {
            AnimatedVectorDrawableCompat.create(context, R.drawable.play_to_stop_anim)
        } else {
            AnimatedVectorDrawableCompat.create(context, R.drawable.stop_to_play_anim)
        }
        fab.setImageDrawable(avd)

        avd?.start()

        fab.isEnabled = true
    }
}