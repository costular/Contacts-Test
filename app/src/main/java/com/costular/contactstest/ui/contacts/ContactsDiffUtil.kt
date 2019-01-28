package com.costular.contactstest.ui.contacts

import androidx.recyclerview.widget.DiffUtil
import com.costular.contactstest.domain.model.Contact

class ContactsDiffUtil : DiffUtil.ItemCallback<Contact>() {

    override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean =
        oldItem == newItem
}