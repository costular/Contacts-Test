package com.costular.contactstest.data.model

import android.net.Uri

data class ContactEntity(
    val id: Long,
    val name: String,
    val isStarred: Boolean,
    val photo: Uri,
    val photoThumbnail: Uri,
    val emails: List<String>,
    val phones: List<String>
)