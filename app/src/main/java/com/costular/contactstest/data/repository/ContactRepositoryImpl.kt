package com.costular.contactstest.data.repository

import com.costular.contactstest.data.repository.datasource.ContactLocalDataSource
import com.costular.contactstest.domain.model.Contact
import com.costular.contactstest.domain.repository.ContactRepository
import io.reactivex.Single
import javax.inject.Inject

class ContactRepositoryImpl @Inject constructor(
    private val contactLocalDataSource: ContactLocalDataSource
) : ContactRepository {

    override fun getContacts(): Single<List<Contact>> = contactLocalDataSource.getContacts()

    override fun getContactById(contactId: Int): Single<Contact> = contactLocalDataSource.getContactById(contactId)

}