package com.cogitator.googletransitionapi.view.main

import com.cogitator.googletransitionapi.helpers.exceptions.CustomException
import com.cogitator.googletransitionapi.model.models.TransitionEvent
import com.cogitator.googletransitionapi.view.base.MvpView

/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 28/06/2018 (MM/DD/YYYY)
 */
interface MainActivityView : MvpView {

    fun startTransitionService()

    fun stopTransitionService()

    fun showProgress()

    fun hideProgress()

    fun showResult(transitions: List<TransitionEvent>)

    fun showErrorSnack(exception: CustomException)
}