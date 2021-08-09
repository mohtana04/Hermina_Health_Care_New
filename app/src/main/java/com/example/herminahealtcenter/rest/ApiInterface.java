package com.example.herminahealtcenter.rest;

import com.example.herminahealtcenter.model.HistoryfisioheaderResponse;
import com.example.herminahealtcenter.model.HistoryktkheaderResponse;
import com.example.herminahealtcenter.model.HistorylabheaderResponse;
import com.example.herminahealtcenter.model.HistoryradheaderResponse;
import com.example.herminahealtcenter.model.HistoryrwiResponse;
import com.example.herminahealtcenter.model.HistoryrwjResponse;
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

    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("info/historyrwj")
    Call<HistoryrwjResponse> hrwj(@Field("nomr") String nomr);

    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("info/historyrwi")
    Call<HistoryrwiResponse> hrwi(@Field("nomr") String nomr);

    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("info/historylabh")
    Call<HistorylabheaderResponse> hlabhead(@Field("nomr") String nomr);

    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("info/historyradh")
    Call<HistoryradheaderResponse> hradhead(@Field("nomr") String nomr);

    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("info/historyfish")
    Call<HistoryktkheaderResponse> hktkhead(@Field("nomr") String nomr);

    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("info/historyfish")
    Call<HistoryfisioheaderResponse> hfishead(@Field("nomr") String nomr);

}
