package com.list.contact.example.contactlist.api;

import com.list.contact.example.contactlist.model.SendMoneyForm;
import com.list.contact.example.contactlist.model.Transfer;
import com.list.contact.example.contactlist.model.User;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wesley on 9/1/16.
 */
public class ContactListService {
    private static final String ENDPOINT = "http://processoseletivoneon.azurewebsites.net/";
    private ContactListApi contactListApi;

    public ContactListService() {
        Retrofit retrofit = getRetrofitConfiguration();
        contactListApi = retrofit.create(ContactListApi.class);
    }

    public void generateToken(User user, Subscriber<String> subscriber) {
        Observable<String> tokenObservable = contactListApi.generateToken(user.getName(), user.getEmail());

        tokenObservable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void sendMoney(SendMoneyForm form, Subscriber<Boolean> subscriber) {
        Observable<Boolean> sendMoneyObservable = contactListApi.sendMoney(form);

        sendMoneyObservable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getTransfers(String token, Subscriber<List<Transfer>> subscriber) {
        Observable<List<Transfer>> transferObservable = contactListApi.getTransfers(token);

        transferObservable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    private Retrofit getRetrofitConfiguration() {
        return new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
