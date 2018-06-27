package com.cogitator.googletransitionapi.helpers.rx

/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 27/06/2018 (MM/DD/YYYY)
 */
object RxBus {    /**
 * Used to hold all subscriptions for Bus events and un-subscribe properly when needed.
 */
private val subscriptionsMap: HashMap<Any, CompositeDisposable?> by lazy {
    HashMap<Any, CompositeDisposable?>()
}

    /**
     * Avoid using this property directly, exposed only because it's used in inline fun
     */
    val publisher: PublishSubject<Any> = PublishSubject.create<Any>()

    /**
     * Publish the event
     */
    fun publish(event: Any) {
        publisher.onNext(event)
    }

    /**
     * Start listening to events of a specific type.
     * Using ofType we filter only events that match that class type
     *
     * @return          an Observable of type T
     */
    inline fun <reified T : Any> listen(): Observable<T> = publisher.ofType(T::class.java)

    /**
     * Unregisters subscriber from Bus events.
     * Calls un-subscribe method of your subscriptions
     *
     * @param subscriber subscriber to unregister
     */
    fun unregister(subscriber: Any) {
        val compositeSubscription = subscriptionsMap[subscriber]
        if (compositeSubscription == null) {
            Timber.d("Trying to unregister subscriber that wasn't registered")
        } else {
            compositeSubscription.clear()
            subscriptionsMap.remove(subscriber)
        }
    }

    /**
     * Registers subscriber with Bus events.
     *
     * @param subscriber    subscriber to unregister
     * @param disposable    disposable that should be generated and added to the map
     */
    internal fun register(subscriber: Any, disposable: Disposable) {
        var compositeSubscription = subscriptionsMap[subscriber]
        if (compositeSubscription == null) {
            compositeSubscription = CompositeDisposable()
        }
        compositeSubscription.add(disposable)
        subscriptionsMap[subscriber] = compositeSubscription
    }
}

/**
 * Registers the subscription to correctly unregister it later to avoid memory leaks
 *
 * @param subscriber subscriber object that owns this subscription
 */
fun Disposable.registerInBus(subscriber: Any) {
    RxBus.register(subscriber, this)
}