package com.costular.contactstest.ui.util

import android.app.Activity
import android.content.Intent
import androidx.core.app.ActivityCompat
import com.costular.contactstest.ui.contactdetail.ContactDetailActivity

object Navigator {

    fun navigateToContactDetail(activity: Activity, contactId: Int, sharedElementHelper: SharedElementHelper) {
        val intent = Intent(activity, ContactDetailActivity::class.java).apply {
            putExtra(ContactDetailActivity.PARAM_CONTACT_ID, contactId)
        }

        ActivityCompat.startActivity(activity, intent, sharedElementHelper.applyToIntent(activity))
    }

}