package com.costular.contactstest.util

import android.database.Cursor
import com.costular.contactstest.data.model.mapper.CursorMapper
import io.reactivex.Observable

class RxCursor<T> private constructor(private val cursorObservable: Observable<Cursor>) {

    lateinit var observable: Observable<T>

    private fun <T> applyMapper(cursorMapper: CursorMapper<T>): Observable<T> {
        return cursorObservable.map { cursorMapper.transform(it) }
    }

    companion object {
        fun <T> from(cursor: Cursor, cursorMapper: CursorMapper<T>): RxCursor<T> {
            val cursorObservable = Observable.create<Cursor> {
                cursor.moveToFirst()
                while (cursor.moveToNext() && !it.isDisposed) {
                    it.onNext(cursor)
                }
                cursor.close()

                if (!it.isDisposed) {
                    it.onComplete()
                }
            }
            return RxCursor<T>(cursorObservable).apply {
                observable = applyMapper(cursorMapper)
            }
        }
    }

}