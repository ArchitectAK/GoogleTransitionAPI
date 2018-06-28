package com.cogitator.googletransitionapi

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.cogitator.googletransitionapi.helpers.exceptions.CustomException
import com.cogitator.googletransitionapi.helpers.exceptions.toMessage
import com.cogitator.googletransitionapi.helpers.rx.EventType
import com.cogitator.googletransitionapi.helpers.rx.RxBus
import com.cogitator.googletransitionapi.helpers.rx.RxEvent
import com.cogitator.googletransitionapi.helpers.rx.registerInBus
import com.cogitator.googletransitionapi.helpers.utils.startServiceForeground
import com.cogitator.googletransitionapi.model.models.TransitionEvent
import com.cogitator.googletransitionapi.view.base.BaseActivity
import com.cogitator.googletransitionapi.view.main.adapter.TransitionsAdapter
import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.indeterminateProgressDialog
import javax.inject.Inject

class MainActivity : BaseActivity(), MainActivityView {

    @Inject
    lateinit var presenter: MainActivityPresenter

    @Inject
    lateinit var transitionServiceIntent: Intent

    @Inject
    lateinit var disposable: CompositeDisposable

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var transitionsAdapter: TransitionsAdapter

    private val progressDialog by lazy {
        indeterminateProgressDialog(
                message = R.string.progress_dialog_msg,
                title = R.string.extract_progress_dialog_title) {
            setCancelable(false)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set content layout
        setContentView(R.layout.activity_main)

        // Attach presenter
        presenter.attachView(this)

        presenter.prepareFab(this, fab)

        fab.setOnClickListener {
            presenter.toggleTransitionUpdate()
        }

        setupRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        presenter.loadData(disposable)
    }

    override fun onPause() {
        // Clear disposable
        disposable.clear()
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        // Detach presenter
        presenter.detachView()
    }

    /**
     * Helper function to listen for RxEvent and register subscriber within the pool
     */
    override fun subscribeToEvents() {
        RxBus.listen<RxEvent>()
                .subscribe { event ->
                    when (event.type) {
                        EventType.TRANSITION_UPDATE -> presenter.animateFab(this, fab)
                    }
                }
                .registerInBus(this)
    }

    private fun setupRecyclerView() {
        // Setup RecyclerView
        recycler_view?.apply {
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            adapter = transitionsAdapter
            layoutManager = linearLayoutManager
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (dy > 0 && fab?.visibility == View.VISIBLE) {
                        fab?.hide()
                    } else if (dy < 0 && fab?.visibility != View.VISIBLE) {
                        fab?.show()
                    }
                }
            })
        }
    }

    /* MvpView methods */
    override fun showProgress() {
        progressDialog.show()
    }

    override fun hideProgress() {
        progressDialog.dismiss()
    }

    override fun startTransitionService() {
        // Start service, taking into account Android version
        startServiceForeground(transitionServiceIntent)
        // Disable fab
        fab.isEnabled = false
    }

    override fun stopTransitionService() {
        stopService(transitionServiceIntent)
        // Disable fab
        fab.isEnabled = false
    }

    override fun showResult(transitions: List<TransitionEvent>) {
        transitionsAdapter.transitionItems = transitions
    }

    override fun showErrorSnack(exception: CustomException) {
        val msg = exception.toMessage(this)
        val snackbar = Snackbar.make(home_coordinator, msg, Snackbar.LENGTH_LONG)
        snackbar.setAction(android.R.string.ok) {
            snackbar.dismiss()
        }
        snackbar.show()
    }
}