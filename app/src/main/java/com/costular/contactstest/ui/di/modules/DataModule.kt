package com.costular.contactstest.ui.di.modules

import com.costular.contactstest.data.model.mapper.ContactEntityMapper
import com.costular.contactstest.data.model.mapper.ContactMapper
import com.costular.contactstest.data.repository.ContactRepositoryImpl
import com.costular.contactstest.data.repository.datasource.ContactLocalDataSource
import com.costular.contactstest.domain.repository.ContactRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun contactEntityMapper(): ContactEntityMapper = ContactEntityMapper()

    @Singleton
    @Provides
    fun contactMapper(): ContactMapper = ContactMapper()

    @Singleton
    @Provides
    fun contactRepository(contactLocalDataSource: ContactLocalDataSource): ContactRepository =
        ContactRepositoryImpl(contactLocalDataSource)

}