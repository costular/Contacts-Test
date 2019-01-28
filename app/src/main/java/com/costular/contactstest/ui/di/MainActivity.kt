package com.costular.contactstest.ui.di

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.costular.contactstest.R
import com.costular.contactstest.ui.base.BaseActivity
import com.costular.contactstest.ui.contacts.ContactsFragment
import com.tbruyelle.rxpermissions2.RxPermissions

class MainActivity : BaseActivity() {

    val rxPermissions by lazy(LazyThreadSafetyMode.NONE) {
        RxPermissions(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpToolbar(false)
        setTitle(R.string.app_name)

        checkPermissions {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, ContactsFragment())
                .commitAllowingStateLoss()
        }
    }

    private fun checkPermissions(success: () -> Unit) {
        rxPermissions
            .request(Manifest.permission.READ_CONTACTS)
            .subscribe { granted ->
                if (granted) {
                    success.invoke()
                } else {
                    // TODO show dialog saying we need this permission!
                }
            }
    }
}
