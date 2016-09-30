package com.list.contact.example.contactlist.history;

import com.list.contact.example.contactlist.base.BasePresenter;
import com.list.contact.example.contactlist.base.BaseView;
import com.list.contact.example.contactlist.model.Transfer;

import java.util.List;

/**
 * Created by wesley on 9/3/16.
 */
public interface HistoryContract {

    interface View extends BaseView<Presenter> {
        void historyListReceived(List<Transfer> transferList);
    }

    interface Presenter extends BasePresenter {
        void getHistory(String token);
    }
}
