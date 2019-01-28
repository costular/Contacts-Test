package com.costular.contactstest.data.repository

import com.costular.contactstest.data.model.ModelFixtures
import com.costular.contactstest.data.repository.datasource.ContactLocalDataSource
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class ContactRepositoryImplTest {

    lateinit var contactRepositoryImpl: ContactRepositoryImpl

    val fakeContact = ModelFixtures.createFakeContact()

    val contactLocalDataSource: ContactLocalDataSource = mock {
        on { getContacts() }.thenReturn(Single.just(listOf(fakeContact)))
        on { getContactById(1) }.thenReturn(Single.just(fakeContact))
    }

    @Before
    fun setUp() {
        contactRepositoryImpl = ContactRepositoryImpl(contactLocalDataSource)
    }

    @Test
    fun `test retrieve contacts`() {
        val result = contactRepositoryImpl.getContacts()

        verify(contactLocalDataSource).getContacts()

        result
            .test()
            .assertValue { it.first().name == fakeContact.name }
            .assertNoErrors()
    }

    @Test
    fun `test retrieve a single contact by its id`() {
        val result = contactRepositoryImpl.getContactById(1)

        verify(contactLocalDataSource).getContactById(1)

        result
            .test()
            .assertValue { it.name == fakeContact.name }
            .assertNoErrors()
    }

}