package com.list.contact.example.contactlist.contactlist;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.list.contact.example.contactlist.R;
import com.list.contact.example.contactlist.model.Contact;
import com.list.contact.example.contactlist.storage.UserStorage;
import com.list.contact.example.contactlist.util.PicassoCache;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wesley on 9/3/16.
 */
public class ContactListViewHolder extends RecyclerView.ViewHolder {

    private ContactListContract.Presenter parentPresenter;
    private Contact contact;

    @BindView(R.id.txtContactName) AppCompatTextView txtContactName;
    @BindView(R.id.imgContact) AppCompatImageView imgContact;
    @BindView(R.id.viewGroupUserName) RelativeLayout viewGroupUserName;
    @BindView(R.id.txtContactPhone) AppCompatTextView txtContactPhone;
    @BindView(R.id.btnOpenTransfer) AppCompatButton btnOpenTransfer;
    @BindView(R.id.txtTransferValue) AppCompatEditText txtTransferValue;
    @BindView(R.id.btnCancelTransfer) AppCompatButton btnCancelTransfer;
    @BindView(R.id.btnConfirmTransfer) AppCompatButton btnConfirmTransfer;
    @BindView(R.id.contactListCardView) ContactListCardView contactListCardView;
    @BindView(R.id.txtUserInitials) AppCompatTextView txtUserInitials;

    public ContactListViewHolder(View itemView, ContactListContract.Presenter parentPresenter) {
        super(itemView);
        this.parentPresenter = parentPresenter;
        ButterKnife.bind(this, itemView);

        configureButtonClick();
    }

    private void configureButtonClick() {
        btnOpenTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtTransferValue.requestFocus();
                InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
                contactListCardView.toggleCardViewHeight(ContactListViewHolder.this);
            }
        });
        btnCancelTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(),0);
                contactListCardView.toggleCardViewHeight(ContactListViewHolder.this);
                txtTransferValue.setText("");
                txtTransferValue.setError(null);
            }
        });
        btnConfirmTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(txtTransferValue.getText())) {
                    int ecolor = Color.WHITE;
                    String estring = view.getResources().getString(R.string.empty_value_error);
                    ForegroundColorSpan fgcspan = new ForegroundColorSpan(ecolor);
                    SpannableStringBuilder ssbuilder = new SpannableStringBuilder(estring);
                    ssbuilder.setSpan(fgcspan, 0, estring.length(), 0);

                    txtTransferValue.requestFocus();
                    txtTransferValue.setError(ssbuilder);
                } else {
                    InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(),0);
                    Double value = Double.valueOf(txtTransferValue.getText().toString());
                    parentPresenter.sendMoney(UserStorage.getInstance().getToken(view.getContext()), contact, value);
                }
            }
        });
    }

    public void refreshData(Contact contact) {
        this.contact = contact;
        txtContactName.setText(contact.getName());
        txtContactPhone.setText(contact.getPhone());
        configureUserImage();
    }

    private void configureUserImage() {
        if(contact.getPhotoUrl() != null) {
            imgContact.setVisibility(View.VISIBLE);
            viewGroupUserName.setVisibility(View.INVISIBLE);

            PicassoCache.getInstance(imgContact.getContext())
                    .load(contact.getPhotoUrl())
                    .into(imgContact);
            imgContact.setScaleType(ImageView.ScaleType.CENTER_CROP);

        } else {
            viewGroupUserName.setVisibility(View.VISIBLE);
            imgContact.setVisibility(View.INVISIBLE);
            String[] name = contact.getName().split(" ");
            StringBuilder initials = new StringBuilder();
            initials.append(name[0].charAt(0));
            initials.append(name[1].charAt(0));
            txtUserInitials.setText(initials);
        }
    }

    public void showViewContent() {
        btnOpenTransfer.setVisibility(View.VISIBLE);
    }

    public void hideViewContent() {
        btnOpenTransfer.setVisibility(View.GONE);
    }


}
