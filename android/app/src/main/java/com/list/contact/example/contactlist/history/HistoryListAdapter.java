package com.list.contact.example.contactlist.history;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.list.contact.example.contactlist.R;
import com.list.contact.example.contactlist.mock.ContactListMock;
import com.list.contact.example.contactlist.model.Contact;
import com.list.contact.example.contactlist.model.Transfer;
import com.list.contact.example.contactlist.util.PicassoCache;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by wesley on 9/3/16.
 */
public class HistoryListAdapter extends BaseAdapter {
    private List<Transfer> transferList;
    private Context context;

    @BindView(R.id.imgUser) CircleImageView imgUser;
    @BindView(R.id.txtTransferDate) TextView txtTransferDate;
    @BindView(R.id.txtUserName) TextView txtUserName;
    @BindView(R.id.txtTransferValue) TextView txtTransferValue;
    @BindView(R.id.txtTransferTime) TextView txtTransferTime;
    @BindView(R.id.containerNameInitials) RelativeLayout containerNameInitials;
    @BindView(R.id.txtNameInitials) TextView txtNameInitials;

    public HistoryListAdapter(List<Transfer> transferList, Context context) {
        this.transferList = transferList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.transferList.size();
    }

    @Override
    public Object getItem(int i) {
        return this.transferList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return this.transferList.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.history_list_item, null);
        }
        ButterKnife.bind(this, view);

        Transfer transfer = (Transfer) getItem(i);
        configureView(transfer);

        return view;
    }

    private void configureView(Transfer transfer) {
        Contact contact = transfer.getContact(ContactListMock.getContactList());
        txtUserName.setText(contact.getName());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS");
        try {
            Date transferDate = sdf.parse(transfer.getDate());
            SimpleDateFormat dayFormat = new SimpleDateFormat("dd MMM yyyy");
            SimpleDateFormat hourFormat = new SimpleDateFormat("kk:mm");
            txtTransferDate.setText(dayFormat.format(transferDate));
            txtTransferTime.setText(hourFormat.format(transferDate));
        } catch (Exception e) {
            e.printStackTrace();
        }

        configureUserImage(contact);
        StringBuilder value = new StringBuilder("R$");
        value.append(String.format("%.2f", transfer.getValue()) );
        txtTransferValue.setText(value);
    }

    private void configureUserImage(Contact contact) {
        if(contact.getPhotoUrl() != null) {
            imgUser.setVisibility(View.VISIBLE);
            containerNameInitials.setVisibility(View.INVISIBLE);
            PicassoCache.getInstance(imgUser.getContext())
                    .load(contact.getPhotoUrl())
                    .into(imgUser);
        } else {
            containerNameInitials.setVisibility(View.VISIBLE);
            imgUser.setVisibility(View.INVISIBLE);
            String[] name = contact.getName().split(" ");
            StringBuilder initials = new StringBuilder();
            initials.append(name[0].charAt(0));
            initials.append(name[1].charAt(0));
            txtNameInitials.setText(initials);
        }
    }
}
