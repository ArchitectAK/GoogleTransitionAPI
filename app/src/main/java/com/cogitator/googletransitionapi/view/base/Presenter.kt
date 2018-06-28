package com.cogitator.googletransitionapi.view.base

/**
 * Every presenter in the app must either implement this interface or extend BasePresenter
 * indicating the MvpView type that wants to be attached with.
 *
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 28/06/2018 (MM/DD/YYYY)
 */
interface Presenter<in V : MvpView> {

    fun attachView(mvpView: V)

    fun detachView()
}