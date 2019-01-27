package com.costular.contactstest.domain.model

import android.net.Uri

data class Contact(
    val id: Long,
    val name: String,
    val isStarred: Boolean,
    val photo: Uri,
    val photoThumbnail: Uri,
    val emails: Set<String>,
    val phones: Set<String>
)