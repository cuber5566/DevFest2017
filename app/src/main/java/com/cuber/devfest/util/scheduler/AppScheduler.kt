package com.cuber.devfest.util.scheduler

import io.reactivex.Scheduler

/**
 * Allow providing different types of [Scheduler]s.
 */
interface AppScheduler {

    fun computation(): Scheduler

    fun io(): Scheduler

    fun ui(): Scheduler
}
