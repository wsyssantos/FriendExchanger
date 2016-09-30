package com.list.contact.example.contactlist.base;

/**
 * Created by wesley on 9/3/16.
 */
public interface BaseView<T> {
    void setPresenter(T presenter);
    void showLoading();
    void hideLoading();
    void errorReceived(Throwable e);
}
