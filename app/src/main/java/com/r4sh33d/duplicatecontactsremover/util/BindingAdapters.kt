package com.r4sh33d.duplicatecontactsremover.util

import android.util.SparseBooleanArray
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.r4sh33d.duplicatecontactsremover.R
import com.r4sh33d.duplicatecontactsremover.contactsources.ContactSourcesAdapter
import com.r4sh33d.duplicatecontactsremover.duplicatecontact.DuplicateContactsAdapter
import com.r4sh33d.duplicatecontactsremover.model.ContactsAccount

// -- Start ContactsAccounts Fragment

@BindingAdapter("accountsLoadingStatus")
fun bindAccountsLoadingStatus(statusTextView: TextView, status: LoadingStatus?) {
    when (status) {
        LoadingStatus.LOADING -> {
            statusTextView.setText(R.string.loading_contacts_progress_message)
        }
        LoadingStatus.EMPTY -> {
            statusTextView.setText(R.string.loading_contacts_error_message)
        }
        LoadingStatus.DONE -> {
            statusTextView.visibility = View.GONE
        }
        else -> {}
    }
}

@BindingAdapter("accountsLoadingStatus")
fun bindAccountsLoadingStatus(statusProgressBar: ProgressBar, status: LoadingStatus?) {
    when (status) {
        LoadingStatus.LOADING -> statusProgressBar.visibility = View.VISIBLE
        else -> statusProgressBar.visibility = View.GONE
    }
}

@BindingAdapter("contactAccountsList")
fun bindHeaderTextView(headerTextView: TextView, data: List<ContactsAccount>?) {
    headerTextView.visibility = if (data?.size == null || data.isEmpty()) View.GONE else View.VISIBLE
}

@BindingAdapter("contactAccountsList")
fun bindAccountsRecyclerView(recyclerView: RecyclerView, data: List<ContactsAccount>?) {
    data?.let {
        val adapter = recyclerView.adapter as ContactSourcesAdapter
        adapter.updateData(data)
    }
}

// -- End Contacts Accounts Fragment

// -- Start Duplicates Contacts Fragment

@BindingAdapter("duplicatesLoadingStatus")
fun bindDuplicatesLoadingStatus(statusTextView: TextView, status: LoadingStatus?) {
    when (status) {
        LoadingStatus.LOADING -> {
            statusTextView.setText(R.string.finding_duplicate_contacts_loading_message)
        }
        LoadingStatus.EMPTY -> {
            statusTextView.setText(R.string.finding_duplicate_contacts_failure_message)
        }
        LoadingStatus.DONE -> {
            statusTextView.visibility = View.GONE
        }
        else -> {}
    }
}

@BindingAdapter("duplicatesLoadingStatus")
fun bindDuplicatesLoadingStatus(statusProgressBar: ProgressBar, status: LoadingStatus?) {
    when (status) {
        LoadingStatus.LOADING -> statusProgressBar.visibility = View.VISIBLE
        else -> statusProgressBar.visibility = View.GONE
    }
}

@BindingAdapter("duplicatesLoadingStatus")
fun bindDuplicatesLoadingStatus(button: MaterialButton, status: LoadingStatus?) {
    when (status) {
        LoadingStatus.DONE -> button.visibility = View.VISIBLE
        else -> button.visibility = View.GONE
    }
}

@BindingAdapter("duplicateContactList")
fun bindDuplicateContactsRecyclerView(recyclerView: RecyclerView, pair: Pair<List<Any>, SparseBooleanArray>?) {
    pair?.let {
        val adapter = recyclerView.adapter as DuplicateContactsAdapter
        adapter.updateCheckedPositionMarker(pair.second)
        adapter.submitList(pair.first)
        adapter.notifyDataSetChanged()
    }
}

// -- End Duplicates Contacts Fragment
