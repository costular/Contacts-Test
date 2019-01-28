package com.costular.contactstest.ui.contacts

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.costular.contactstest.RxSchedulerRule
import com.costular.contactstest.data.model.ModelFixtures
import com.costular.contactstest.domain.repository.ContactRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ContactsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rxSchedulerRule: RxSchedulerRule = RxSchedulerRule()

    lateinit var contactsViewModel: ContactsViewModel

    val contactsRepository: ContactRepository = mock {
        on { getContacts() }.thenReturn(Single.just(listOf(ModelFixtures.createFakeContact())))
    }

    @Before
    fun setUp() {
        contactsViewModel = ContactsViewModel(contactsRepository)
    }

    @Test
    fun `test retrieve contacts and pass to livedata`() {
        contactsViewModel.loadContacts()

        verify(contactsRepository, times(2)).getContacts()

        with (contactsViewModel.contactsState.value) {
            requireNotNull(this!!)
            val response = ModelFixtures.createFakeContact()

            Assertions.assertThat(first().id == response.id)
            Assertions.assertThat(first().name == response.name)
        }
    }

}