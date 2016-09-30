package com.list.contact.example.contactlist.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.list.contact.example.contactlist.R;
import com.list.contact.example.contactlist.base.BaseActivity;
import com.list.contact.example.contactlist.base.BaseFragment;
import com.list.contact.example.contactlist.contactlist.ContactListActivity;
import com.list.contact.example.contactlist.model.User;
import com.list.contact.example.contactlist.storage.UserStorage;
import com.list.contact.example.contactlist.util.DialogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wesley on 9/3/16.
 */
public class UserFragment extends BaseFragment implements UserContract.View {

    private UserContract.Presenter presenter;
    private User user;

    @BindView(R.id.txtUserName) AppCompatTextView txtUserName;
    @BindView(R.id.txtUserEmail) AppCompatTextView txtUserMail;
    @BindView(R.id.fabShowContactList) FloatingActionButton fabShowContactList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.user_frag, container, false);
        ButterKnife.bind(this, rootView);

        user = new User("wsy.s.santos@gmail.com", "Wesley Santos");

        txtUserName.setText(user.getName());
        txtUserMail.setText(user.getEmail());
        setPresenter(new UserPresenter(this));

        configureButtonClick();
        checkForTokenRequest();

        return rootView;
    }

    private void checkForTokenRequest() {
        presenter.getToken(user);
    }

    private void configureButtonClick() {
        fabShowContactList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int[] location = new int[2];
                fabShowContactList.getLocationOnScreen(location);

                Intent contactListIntent = new Intent(getActivity(), ContactListActivity.class);

                contactListIntent.putExtra(BaseActivity.REVEAL_X, location[0]);
                contactListIntent.putExtra(BaseActivity.REVEAL_Y, location[1]);

                startActivity(contactListIntent);
            }
        });
    }

    @Override
    public void tokenReceived(String token) {
        UserStorage userStorage = UserStorage.getInstance();
        userStorage.setToken(getActivity(), token);
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

    @Override
    public void setPresenter(UserContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
