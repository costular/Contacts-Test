package com.costular.contactstest.data.model.mapper

import android.database.Cursor
import android.net.Uri
import android.provider.ContactsContract
import androidx.core.database.getIntOrNull
import androidx.core.database.getStringOrNull
import androidx.core.net.toUri
import com.costular.contactstest.data.model.ContactEntity

class ContactEntityMapper : CursorMapper<ContactEntity> {

    override fun transform(cursor: Cursor): ContactEntity {
        return ContactEntity(
            cursor.getInt(cursor.getColumnIndex(ContactsContract.Data.CONTACT_ID)),
            cursor.getStringOrNull(cursor.getColumnIndex(ContactsContract.Data.DISPLAY_NAME_PRIMARY)) ?: "",
            cursor.getIntOrNull(cursor.getColumnIndex(ContactsContract.Data.STARRED)) == 1,
            cursor.getStringOrNull(cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.PHOTO_URI))?.toUri(),
            cursor.getStringOrNull(cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.PHOTO_THUMBNAIL_URI))?.toUri()
        )
    }

}