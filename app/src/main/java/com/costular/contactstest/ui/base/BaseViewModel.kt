package com.costular.contactstest.ui.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable


abstract class BaseViewModel : ViewModel() {

    val disposables by lazy(LazyThreadSafetyMode.NONE) {
        CompositeDisposable()
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}