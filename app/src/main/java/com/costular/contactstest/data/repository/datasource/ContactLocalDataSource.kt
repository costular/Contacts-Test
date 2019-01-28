package com.costular.contactstest.data.repository.datasource

import android.content.Context
import android.provider.ContactsContract
import androidx.core.database.getStringOrNull
import com.costular.contactstest.data.model.mapper.ContactEntityMapper
import com.costular.contactstest.data.model.mapper.ContactMapper
import com.costular.contactstest.domain.model.Contact
import com.costular.contactstest.util.RxCursor
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContactLocalDataSource @Inject constructor(
    private val context: Context,
    private val contactEntityMapper: ContactEntityMapper,
    private val contactMapper: ContactMapper
) : ContactDataSource {

    override fun getContacts(): Single<List<Contact>> {
        val projection = arrayOf(
            ContactsContract.Data.CONTACT_ID,
            ContactsContract.CommonDataKinds.StructuredName.PHOTO_URI,
            ContactsContract.CommonDataKinds.StructuredName.PHOTO_THUMBNAIL_URI,
            ContactsContract.CommonDataKinds.StructuredName.STARRED,
            ContactsContract.Data.DISPLAY_NAME,
            ContactsContract.Data.STARRED
        )

        val query = ContactsContract.Data.CONTENT_URI

        val cursor = context.contentResolver.query(
            query,
            projection,
            null,
            null,
            "${ContactsContract.Data.STARRED} DESC, ${ContactsContract.Data.DISPLAY_NAME} ASC"
        )

        requireNotNull(cursor!!) {
            "Cursor can't be null"
        }

        return RxCursor.from(cursor, contactEntityMapper)
            .observable
            .map {
                contactMapper.transform(it)
            }
            .toList()
    }

    override fun getContactById(contactId: Int): Single<Contact> {
        val projection = arrayOf(
            ContactsContract.Data.CONTACT_ID,
            ContactsContract.CommonDataKinds.StructuredName.PHOTO_URI,
            ContactsContract.CommonDataKinds.StructuredName.PHOTO_THUMBNAIL_URI,
            ContactsContract.CommonDataKinds.StructuredName.STARRED,
            ContactsContract.Data.DISPLAY_NAME_PRIMARY,
            ContactsContract.Data.STARRED
        )

        val query = ContactsContract.Data.CONTENT_URI

        val cursor = context.contentResolver.query(
            query,
            projection,
            "${ContactsContract.Data.CONTACT_ID} = ?",
            arrayOf(contactId.toString()),
            null
        )
        requireNotNull(cursor!!) {
            "Cursor can't be null"
        }

        return RxCursor.from(cursor, contactEntityMapper)
            .observable
            .map {
                contactMapper.transform(it)
            }
            .firstOrError()
    }
}