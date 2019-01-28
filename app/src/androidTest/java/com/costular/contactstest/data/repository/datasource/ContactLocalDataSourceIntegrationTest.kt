package com.costular.contactstest.data.repository.datasource

import android.content.ContentProviderOperation
import android.content.ContentValues
import android.content.Context
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.costular.contactstest.data.model.mapper.ContactEntityMapper
import com.costular.contactstest.data.model.mapper.ContactMapper
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import android.provider.ContactsContract
import org.junit.Ignore

/**
 * ATTENTION: This test is not finished
 */
@RunWith(AndroidJUnit4::class)
class ContactLocalDataSourceIntegrationTest {

    lateinit var contactLocalDataSource: ContactLocalDataSource

    @Before
    fun setUp() {
        grantPermissions()
        insertContact()

        contactLocalDataSource = ContactLocalDataSource(getContext(), ContactEntityMapper(), ContactMapper())
    }

    @After
    fun clean() {
        grantPermissions()
        // TODO remove
    }

    @Ignore
    @Test
    fun testRetrieveContactList() {
        contactLocalDataSource
            .getContacts()
            .test()
            .assertValue { it.find { it.name == "Diego" } != null }
            .assertComplete()
    }

    private fun insertContact() {
        val operations = arrayListOf<ContentProviderOperation>()

        val rawId = 100

        operations.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
            .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
            .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null).build())

        operations.add(ContentProviderOperation
            .newInsert(ContactsContract.Data.CONTENT_URI)
            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawId)
            .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
            .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, "Diego")
            .build())

        getContext().contentResolver.applyBatch(ContactsContract.AUTHORITY, operations)
    }

    private fun removeContact() {
        // TODO
    }

    private fun grantPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getInstrumentation().uiAutomation.executeShellCommand(
                """pm grant ${getContext().packageName} android.permission.READ_CONTACTS"""
            )
            getInstrumentation().uiAutomation.executeShellCommand(
                """pm grant ${getContext().packageName} android.permission.WRITE_CONTACTS"""
            )
        }
    }

    private fun getContext(): Context = ApplicationProvider.getApplicationContext()

}