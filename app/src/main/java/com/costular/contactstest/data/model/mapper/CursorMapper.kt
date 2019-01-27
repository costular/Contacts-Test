package com.costular.contactstest.data.model.mapper

import android.database.Cursor

interface CursorMapper<out T> {

    fun transform(cursor: Cursor): T

}