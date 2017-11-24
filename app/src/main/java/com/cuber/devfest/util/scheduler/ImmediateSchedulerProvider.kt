package com.cuber.devfest.util.scheduler

import android.support.annotation.VisibleForTesting
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

/**
 * Implementation of the [AppScheduler] making all [Scheduler]s execute
 * synchronously so we can easily run assertions in our tests.
 *
 *
 * To achieve this, we are using the [io.reactivex.internal.schedulers.TrampolineScheduler] from the [Schedulers] class.
 */
@VisibleForTesting
class ImmediateSchedulerProvider : AppScheduler {

    override fun computation(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun io(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun ui(): Scheduler {
        return Schedulers.trampoline()
    }
}
