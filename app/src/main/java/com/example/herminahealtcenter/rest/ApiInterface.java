package com.example.herminahealtcenter.rest;

import com.example.herminahealtcenter.model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("users/login")
    Call<LoginResponse> a(@Field("nomr") String nomr, @Field("tgllahir") String tgllahir);
}
