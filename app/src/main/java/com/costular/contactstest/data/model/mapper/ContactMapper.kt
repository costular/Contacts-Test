package com.costular.contactstest.data.model.mapper

import com.costular.contactstest.data.model.ContactEntity
import com.costular.contactstest.domain.model.Contact

class ContactMapper : Mapper<ContactEntity, Contact> {

    override fun transform(input: ContactEntity): Contact =
        Contact(
            input.id,
            input.name,
            input.isStarred,
            input.photo,
            input.photoThumbnail
        )

    override fun transformList(inputList: List<ContactEntity>): List<Contact> =
            inputList.map { transform(it) }

}