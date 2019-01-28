package com.costular.contactstest.ui.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val application: Application) {

    @Singleton
    @Provides
    fun application(): Application = application

    @Singleton
    @Provides
    fun context(): Context = application.applicationContext

}