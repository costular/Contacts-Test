package com.costular.contactstest.data.model

import com.costular.contactstest.data.model.ContactEntity
import com.costular.contactstest.domain.model.Contact

object ModelFixtures {

    fun createFakeContact(): Contact =
            Contact(
                1,
                "Diego",
                true,
                null,
                null
            )

    fun createFakeContactEntity(): ContactEntity =
            ContactEntity(
                1,
                "Diego",
                true,
                null,
                null
            )

}