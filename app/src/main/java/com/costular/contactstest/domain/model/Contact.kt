package com.costular.contactstest.domain.model

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class Contact(
    val id: Int,
    val name: String,
    val isStarred: Boolean,
    val photo: Uri?,
    val photoThumbnail: Uri?
)