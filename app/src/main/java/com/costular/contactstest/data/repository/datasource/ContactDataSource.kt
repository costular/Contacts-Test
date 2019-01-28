package com.costular.contactstest.data.repository.datasource

import com.costular.contactstest.domain.model.Contact
import io.reactivex.Observable
import io.reactivex.Single

interface ContactDataSource {

    fun getContacts(): Single<List<Contact>>

    fun getContactById(contactId: Int): Single<Contact>

}