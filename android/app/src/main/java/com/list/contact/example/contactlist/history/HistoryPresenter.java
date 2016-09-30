package com.list.contact.example.contactlist.history;

import com.list.contact.example.contactlist.api.ContactListService;
import com.list.contact.example.contactlist.base.BaseFragment;
import com.list.contact.example.contactlist.model.Transfer;

import java.util.List;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by wesley on 9/3/16.
 */
public class HistoryPresenter implements HistoryContract.Presenter {

    private HistoryContract.View view;
    private List<Transfer> transferList;

    public HistoryPresenter(HistoryContract.View view) {
        this.view = view;
    }

    @Override
    public void getHistory(String token) {
        view.showLoading();
        ContactListService contactListService = new ContactListService();
        contactListService.getTransfers(token, new Subscriber<List<Transfer>>() {
            @Override
            public void onCompleted() {
                view.hideLoading();
                view.historyListReceived(transferList);
            }

            @Override
            public void onError(Throwable e) {
                view.hideLoading();
                if(e instanceof HttpException) {
                    if(((HttpException)e).code() == 400) {
                        view.historyListReceived(null);
                    } else {
                        view.errorReceived(e);
                    }
                } else {
                    view.errorReceived(e);
                }
            }

            @Override
            public void onNext(List<Transfer> transfers) {
                transferList = transfers;
            }
        });
    }
}
