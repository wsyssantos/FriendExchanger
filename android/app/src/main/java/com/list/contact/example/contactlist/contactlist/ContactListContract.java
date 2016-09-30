package com.list.contact.example.contactlist.contactlist;

import com.list.contact.example.contactlist.base.BasePresenter;
import com.list.contact.example.contactlist.base.BaseView;
import com.list.contact.example.contactlist.model.Contact;

import java.util.List;

/**
 * Created by wesley on 9/3/16.
 */
public interface ContactListContract {
    interface View extends BaseView<Presenter> {
        void moneySent(boolean result);
    }

    interface Presenter extends BasePresenter {
        void sendMoney(String token, Contact contact, Double value);
    }
}
