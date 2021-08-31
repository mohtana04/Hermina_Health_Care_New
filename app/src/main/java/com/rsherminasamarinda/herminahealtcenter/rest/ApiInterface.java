package com.rsherminasamarinda.herminahealtcenter.rest;

import com.rsherminasamarinda.herminahealtcenter.model.DetailRacikanResponse;
import com.rsherminasamarinda.herminahealtcenter.model.HistoryFarheaderResponse;
import com.rsherminasamarinda.herminahealtcenter.model.Historyfardetailresponse;
import com.rsherminasamarinda.herminahealtcenter.model.HistoryfisioheaderResponse;
import com.rsherminasamarinda.herminahealtcenter.model.HistoryktkheaderResponse;
import com.rsherminasamarinda.herminahealtcenter.model.HistorylabdetailResponse;
import com.rsherminasamarinda.herminahealtcenter.model.HistorylabheaderResponse;
import com.rsherminasamarinda.herminahealtcenter.model.HistoryradheaderResponse;
import com.rsherminasamarinda.herminahealtcenter.model.HistoryrwiResponse;
import com.rsherminasamarinda.herminahealtcenter.model.HistoryrwjResponse;
import com.rsherminasamarinda.herminahealtcenter.model.LoginResponse;

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
    @POST("info/historyktkh")
    Call<HistoryktkheaderResponse> hktkhead(@Field("nomr") String nomr);

    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("info/historyfish")
    Call<HistoryfisioheaderResponse> hfishead(@Field("nomr") String nomr);

    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("info/historyfarh")
    Call<HistoryFarheaderResponse> hfarhead(@Field("nomr") String nomr);


    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("info/historylabdet")
    Call<HistorylabdetailResponse> hlabdet(@Field("nobuktitransaksi") String nobuktitransaksi);

    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("info/historyfardet")
    Call<Historyfardetailresponse> hfardet(@Field("nobuktitransaksi") String nobuktitransaksi);


    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("info/noracikan")
    Call<DetailRacikanResponse> dataracikan(@Field("noracikan") String noracikan);

}
