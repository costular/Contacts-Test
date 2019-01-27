package com.costular.contactstest.domain.repository

import com.costular.contactstest.domain.model.Contact
import io.reactivex.Single

interface ContactRepository {

    fun getContacts(): Single<List<Contact>>

    fun getContactById(contactId: Long): Single<Contact>

}