package com.list.contact.example.contactlist.contactlist;

import com.list.contact.example.contactlist.api.ContactListService;
import com.list.contact.example.contactlist.mock.ContactListMock;
import com.list.contact.example.contactlist.model.Contact;
import com.list.contact.example.contactlist.model.SendMoneyForm;

import rx.Subscriber;

/**
 * Created by wesley on 9/3/16.
 */
public class ContactListPresenter implements ContactListContract.Presenter {
    private ContactListContract.View view;
    private Boolean moneyReturn;

    public ContactListPresenter(ContactListContract.View view) {
        this.view = view;
    }

    @Override
    public void sendMoney(String token, Contact contact, Double value) {
        view.showLoading();
        ContactListService contactListService = new ContactListService();
        SendMoneyForm form = new SendMoneyForm(contact.getId(), token, value);
        contactListService.sendMoney(form, new Subscriber<Boolean>() {
            @Override
            public void onCompleted() {
                view.hideLoading();
                view.moneySent(moneyReturn);
            }

            @Override
            public void onError(Throwable e) {
                view.hideLoading();
                view.errorReceived(e);
            }

            @Override
            public void onNext(Boolean aBoolean) {
                moneyReturn = aBoolean;
            }
        });
    }
}
