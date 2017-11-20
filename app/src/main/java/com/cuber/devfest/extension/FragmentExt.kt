package com.cuber.devfest.extension

import android.arch.lifecycle.LifecycleObserver
import com.cuber.devfest.ui.base.BaseFragment


fun <T : LifecycleObserver> BaseFragment.setupPresenter(presenter: T, action: T.() -> Unit): T{
    presenter.run {
        action(presenter)
        lifecycle.addObserver(presenter)
    }
    return presenter
}