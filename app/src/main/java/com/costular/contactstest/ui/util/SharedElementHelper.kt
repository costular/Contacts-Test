package com.costular.contactstest.ui.util

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.core.util.Pair
import androidx.core.app.ActivityOptionsCompat
import java.lang.ref.WeakReference

class SharedElementHelper {
    private val sharedElementViews = mutableMapOf<WeakReference<View>, String?>()

    fun addSharedElement(view: View, name: String? = null) {
        sharedElementViews[WeakReference(view)] = name ?: view.transitionName
    }

    fun applyToIntent(activity: Activity): Bundle? {
        return ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity,
                *sharedElementViews.map { Pair(it.key.get(), it.value) }.toList().toTypedArray()
        ).toBundle()
    }

    fun isEmpty(): Boolean = sharedElementViews.isEmpty()
}