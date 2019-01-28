package com.costular.contactstest.ui.contacts

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.costular.contactstest.R
import com.costular.contactstest.domain.model.Contact
import com.costular.contactstest.ui.base.BaseFragment
import com.costular.contactstest.ui.di.ContactsApp
import com.costular.contactstest.ui.util.Navigator
import com.costular.contactstest.ui.util.SharedElementHelper
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.fragment_contacts.*

class ContactsFragment : BaseFragment() {

    lateinit var contactsViewModel: ContactsViewModel

    val adapter: ContactsAdapter by lazy(LazyThreadSafetyMode.NONE) {
        ContactsAdapter(object: ContactsAdapter.Callbacks {
            override fun onContactClicked(contact: Contact, imageView: ImageView) {
                val sharedElements = SharedElementHelper().apply {
                    addSharedElement(imageView, getString(R.string.image_transition))
                }

                Navigator.navigateToContactDetail(activity as Activity, contact.id, sharedElements)
            }

        })
    }

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    private fun inject() {
        (activity?.application as ContactsApp).component.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_contacts, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUp(savedInstanceState)
    }

    private fun setUp(savedInstanceState: Bundle?) {
        setUpRecycler(savedInstanceState)

        contactsViewModel = ViewModelProviders.of(this, viewModelFactory).get(ContactsViewModel::class.java)
        with (contactsViewModel) {
            contactsState.observe(this@ContactsFragment, Observer {
                onContactsLoaded(it)
            })

            isLoadingState.observe(this@ContactsFragment, Observer {
                showLoading(it)
            })
        }
    }

    private fun setUpRecycler(savedInstanceState: Bundle?) {
        with (recyclerContacts) {
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
            adapter = this@ContactsFragment.adapter

            savedInstanceState?.let {
                val scrollPosition = it.getInt(ARG_SCROLL_POSITION, -1)
                if (scrollPosition > -1) {
                    layoutManager?.scrollToPosition(scrollPosition)
                }
            }
        }
    }

    private fun onContactsLoaded(contacts: List<Contact>) {
        adapter.submitList(contacts)
    }

    private fun showLoading(isLoading: Boolean) {
        progressLoading.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(ARG_SCROLL_POSITION, getScrollPosition())
        super.onSaveInstanceState(outState)
    }

    private fun getScrollPosition(): Int {
        return if (recyclerContacts.layoutManager != null && recyclerContacts.layoutManager is LinearLayoutManager) {
            (recyclerContacts.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
        } else {
            -1
        }
    }

    companion object {
        const val ARG_SCROLL_POSITION = "scroll_position"
    }

}