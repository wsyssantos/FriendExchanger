package com.list.contact.example.contactlist.user;

import com.list.contact.example.contactlist.base.BasePresenter;
import com.list.contact.example.contactlist.base.BaseView;
import com.list.contact.example.contactlist.model.User;

/**
 * Created by wesley on 9/3/16.
 */
public interface UserContract {

    interface View extends BaseView<Presenter> {
        void tokenReceived(String token);
    }

    interface Presenter extends BasePresenter {
        void getToken(User user);
    }
}
