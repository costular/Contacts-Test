package com.costular.contactstest.ui.contactdetail

import android.app.Activity
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.costular.contactstest.R
import com.costular.contactstest.domain.model.Contact
import com.costular.contactstest.ui.base.BaseActivity
import com.costular.contactstest.ui.di.ContactsApp
import kotlinx.android.synthetic.main.activity_contact_detail.*
import javax.inject.Inject

class ContactDetailActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var contactDetailViewModel: ContactDetailViewModel

    var contactId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        supportPostponeEnterTransition()
        setContentView(R.layout.activity_contact_detail)
        setUpToolbar(true)
        setTitle("")
        setUp()
    }

    private fun inject() {
        (application as ContactsApp).component.inject(this)
    }

    private fun setUp() {
        contactId = intent?.extras?.getInt(PARAM_CONTACT_ID, -1) ?: -1
        if (contactId == -1) {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }

        contactDetailViewModel = ViewModelProviders.of(this, viewModelFactory).get(ContactDetailViewModel::class.java)

        with(contactDetailViewModel) {
            contactState.observe(this@ContactDetailActivity, Observer {
                onContactLoaded(it)
            })
        }
    }

    override fun onResume() {
        super.onResume()
        contactDetailViewModel.loadContactById(contactId)
    }

    private fun onContactLoaded(contact: Contact) {
        textContactDetailName.text = contact.name
        imageContactDetailStarred.visibility = if (contact.isStarred) View.VISIBLE else View.GONE

        Glide.with(this)
            .load(contact.photo)
            .apply(
                RequestOptions
                    .placeholderOf(R.drawable.ic_contact_big_placeholder)
                    .error(R.drawable.ic_contact_big_placeholder)
            )
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    supportStartPostponedEnterTransition()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    supportStartPostponedEnterTransition()
                    return false
                }

            })
            .into(imageContactDetailAvatar)

    }

    companion object {
        const val PARAM_CONTACT_ID = "contact_id"
    }

}