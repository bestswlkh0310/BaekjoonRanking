package com.bestswlkh0310.presentation.base

import android.app.Service
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseService: Service() {
    private val compositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    fun add(d: Disposable) {
        compositeDisposable.add(d)
    }
}