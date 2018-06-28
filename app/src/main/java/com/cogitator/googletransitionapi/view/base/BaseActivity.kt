package com.cogitator.googletransitionapi.view.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.cogitator.googletransitionapi.helpers.rx.RxBus
import dagger.android.AndroidInjection

/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 28/06/2018 (MM/DD/YYYY)
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // Inject external dependencies
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        // Subscribe to events
        subscribeToEvents()
    }

    /**
     * Base method to subscribe to RxEvents
     */
    open fun subscribeToEvents() {

    }

    override fun onDestroy() {
        // Un-subscribe from bus events
        RxBus.unregister(this)
        super.onDestroy()
    }
}