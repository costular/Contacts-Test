package com.costular.contactstest.ui.di.components

import com.costular.contactstest.ui.contactdetail.ContactDetailActivity
import com.costular.contactstest.ui.contacts.ContactsFragment
import com.costular.contactstest.ui.di.modules.AppModule
import com.costular.contactstest.ui.di.modules.DataModule
import com.costular.contactstest.ui.di.modules.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(contactsFragment: ContactsFragment)
    fun inject(contactDetailActivity: ContactDetailActivity)

}