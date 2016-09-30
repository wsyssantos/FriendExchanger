package com.list.contact.example.contactlist.contactlist;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.list.contact.example.contactlist.R;
import com.list.contact.example.contactlist.base.BaseFragment;
import com.list.contact.example.contactlist.mock.ContactListMock;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wesley on 9/3/16.
 */
public class ContactListFragment extends BaseFragment implements ContactListContract.View {

    private ContactListContract.Presenter presenter;
    private ContactListAdapter contactListAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @BindView(R.id.contactListRecyclerView) RecyclerView contactListRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.contact_list_frag, container, false);
        ButterKnife.bind(this, rootView);

        contactListRecyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        contactListRecyclerView.setLayoutManager(layoutManager);

        setPresenter(new ContactListPresenter(this));
        contactListAdapter = new ContactListAdapter(ContactListMock.getContactList(), presenter);
        contactListAdapter.setHasStableIds(true);
        contactListRecyclerView.setAdapter(contactListAdapter);

        return rootView;
    }

    @Override
    public void moneySent(boolean result) {
        if(result) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Transferência realizada com sucesso!")
                    .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .show();

        }
    }

    @Override
    public void setPresenter(ContactListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showLoading() {
        showDialog();
    }

    @Override
    public void hideLoading() {
        hideDialog();
    }

    @Override
    public void errorReceived(Throwable e) {
        showErrorDialog(e);
    }
}