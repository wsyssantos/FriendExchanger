package com.list.contact.example.contactlist.user;

import com.list.contact.example.contactlist.api.ContactListService;
import com.list.contact.example.contactlist.model.User;

import rx.Subscriber;

/**
 * Created by wesley on 9/3/16.
 */
public class UserPresenter implements UserContract.Presenter {

    private UserContract.View view;
    private String token;

    public UserPresenter(UserContract.View view) {
        this.view = view;
    }

    @Override
    public void getToken(User user) {
        ContactListService contactListService = new ContactListService();
        view.showLoading();
        contactListService.generateToken(user, new Subscriber<String>() {
            @Override
            public void onCompleted() {
                view.hideLoading();
                view.tokenReceived(getToken());
            }

            @Override
            public void onError(Throwable e) {
                view.hideLoading();
                view.errorReceived(e);
            }

            @Override
            public void onNext(String s) {
                setToken(s);
            }
        });
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
