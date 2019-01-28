package com.costular.contactstest.ui.contacts

import androidx.lifecycle.MutableLiveData
import com.costular.contactstest.domain.model.Contact
import com.costular.contactstest.domain.repository.ContactRepository
import com.costular.contactstest.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class ContactsViewModel @Inject constructor(
    private val repository: ContactRepository
) : BaseViewModel() {

    val contactsState = MutableLiveData<List<Contact>>()
    val isLoadingState = MutableLiveData<Boolean>()

    init {
        loadContacts()
    }

    fun loadContacts() {
        repository
            .getContacts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isLoadingState.value = true }
            .doFinally { isLoadingState.value = false }
            .subscribeBy(
                onSuccess = {
                    contactsState.value = it
                },
                onError = {
                    Timber.e(it)
                }
            )
            .addTo(disposables)
    }

}