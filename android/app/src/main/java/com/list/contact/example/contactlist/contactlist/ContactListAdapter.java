package com.list.contact.example.contactlist.contactlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.list.contact.example.contactlist.R;
import com.list.contact.example.contactlist.model.Contact;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by wesley on 9/3/16.
 */
public class ContactListAdapter extends RecyclerView.Adapter<ContactListViewHolder> {

    private List<Contact> contactList;
    private ContactListContract.Presenter parentPresenter;
    private ContactListViewHolder contactListViewHolder;
    public ContactListAdapter(List<Contact> contactList, ContactListContract.Presenter parentPresenter) {
        this.contactList = contactList;
        this.parentPresenter = parentPresenter;
    }

    @Override
    public ContactListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_list_item, parent, false);
        contactListViewHolder = new ContactListViewHolder(view, parentPresenter);
        return contactListViewHolder;
    }

    @Override
    public void onBindViewHolder(ContactListViewHolder holder, int position) {
        holder.refreshData(contactList.get(position));
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    @Override
    public long getItemId(int position) {
        return this.contactList.get(position).getId().hashCode();
    }
}
