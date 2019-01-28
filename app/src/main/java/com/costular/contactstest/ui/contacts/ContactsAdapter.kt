package com.costular.contactstest.ui.contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.costular.contactstest.R
import com.costular.contactstest.domain.model.Contact
import kotlinx.android.synthetic.main.item_contact.view.*

class ContactsAdapter(
    val listener: Callbacks
) : ListAdapter<Contact, ContactsAdapter.ContactsViewHolder>(ContactsDiffUtil()) {

    interface Callbacks {
        fun onContactClicked(contact: Contact, imageView: ImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return ContactsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ContactsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(contact: Contact) {
            with(itemView) {
                textContactName.text = contact.name

                Glide.with(context)
                    .load(contact.photoThumbnail)
                    .apply(
                        RequestOptions()
                            .circleCrop()
                            .placeholder(R.drawable.ic_contact_placeholder)
                            .error(R.drawable.ic_contact_placeholder)
                    )
                    .into(imageContactAvatar)

                imageStarred.visibility = if (contact.isStarred) View.VISIBLE else View.GONE

                setOnClickListener {
                    listener.onContactClicked(contact, imageContactAvatar)
                }
            }
        }

    }
}