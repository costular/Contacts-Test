package com.costular.contactstest.ui.contactdetail

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

class ContactDetailViewModel @Inject constructor(
    private val repository: ContactRepository
) : BaseViewModel() {

    val contactState = MutableLiveData<Contact>()

    fun loadContactById(contactId: Int) {
        repository.getContactById(contactId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    contactState.value = it
                },
                onError = {
                    Timber.e(it)
                }
            )
            .addTo(disposables)
    }

}