package com.costular.contactstest.ui.di

import android.app.Application
import com.costular.contactstest.BuildConfig
import com.costular.contactstest.ui.di.components.AppComponent
import com.costular.contactstest.ui.di.components.DaggerAppComponent
import com.costular.contactstest.ui.di.modules.AppModule
import com.costular.contactstest.ui.di.modules.DataModule
import timber.log.Timber

class ContactsApp : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .dataModule(DataModule())
            .build()
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}