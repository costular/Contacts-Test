package com.costular.contactstest.data.model

import android.net.Uri

data class ContactEntity(
    val id: Int,
    val name: String,
    val isStarred: Boolean,
    val photo: Uri?,
    val photoThumbnail: Uri?
)