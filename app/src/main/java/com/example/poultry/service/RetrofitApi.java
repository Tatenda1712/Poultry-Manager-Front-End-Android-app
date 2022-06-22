package com.example.poultry.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitApi {

    @POST("register")
    @FormUrlEncoded
    Call<ResponseBody> createUser(@Field("fname")  String fname,
                                    @Field("lname")  String lname,
                                    @Field("phone")  String phone,
                                    @Field("email")  String email,
                                    @Field("password")  String password);
    @POST("login")
    @FormUrlEncoded
    Call<ResponseBody> authenticate(
            @Field("email")  String email,
            @Field("password")  String password);


    @POST("addeggs")
    @FormUrlEncoded
    Call<ResponseBody> addEggs(
            @Field("batch_name")  String batch_name,
            @Field("quantity")  String quantity);

    @POST("addfeeds")
    @FormUrlEncoded
    Call<ResponseBody> addFeeds(
            @Field("batch_name")  String batch_name,
            @Field("quantity")  String quantity,
            @Field("cost")  String cost,
            @Field("purpose")  String purpose
           );

    @POST("addflock")
    @FormUrlEncoded
    Call<ResponseBody> addFlock(
            @Field("batch_name")  String batch_name,
            @Field("quantity")  String quantity,
            @Field("purpose")  String purpose
           );


    @POST("addtransactions")
    @FormUrlEncoded
    Call<ResponseBody> addTransactions(
            @Field("purpose")  String purpose,
            @Field("amount")  String amount,
            @Field("profitloss")  String profitloss);


    @GET("getflock")
    Call<ResponseBody> getFlock();
    @GET("geteggs")
    Call<ResponseBody> getEggs();
    @GET("gettransactions")
    Call<ResponseBody> getTransactions();
    @GET("getfeeds")
    Call<ResponseBody> getFeeds();
}
