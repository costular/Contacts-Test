package com.costular.contactstest.data.model

import com.costular.contactstest.data.model.mapper.ContactMapper
import com.costular.contactstest.domain.model.Contact
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test

class ContactMapperTest {

    lateinit var contactMapper: ContactMapper

    @Before
    fun setUp() {
        contactMapper = ContactMapper()
    }

    @Test
    fun `test transform contact entity`() {
        // Given
        val contactEntity = ModelFixtures.createFakeContactEntity()

        // When
        val contact = contactMapper.transform(contactEntity)

        // Then
        Assertions.assertThat(contact).isInstanceOf(Contact::class.java)
        Assertions.assertThat(contact.id).isEqualTo(contactEntity.id)
        Assertions.assertThat(contact.name).isEqualTo(contactEntity.name)
        Assertions.assertThat(contact.isStarred).isEqualTo(contactEntity.isStarred)
    }

}