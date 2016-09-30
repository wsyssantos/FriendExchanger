package com.list.contact.example.contactlist.api;

import com.list.contact.example.contactlist.model.SendMoneyForm;
import com.list.contact.example.contactlist.model.Transfer;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by wesley on 9/1/16.
 */
public interface ContactListApi {
    @GET("GenerateToken")
    Observable<String> generateToken(@Query("nome") String name, @Query("email") String email);

    @POST("SendMoney")
    Observable<Boolean> sendMoney(@Body SendMoneyForm sendMoneyForm);

    @GET("GetTransfers")
    Observable<List<Transfer>> getTransfers(@Query("token") String token);
}
