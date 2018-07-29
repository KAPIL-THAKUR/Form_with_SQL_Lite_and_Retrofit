package com.example.kapil.formtask1;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {

    @POST("/democalltesting")
    @FormUrlEncoded
    Call<User> savePost(@Field("requestby") String requestby);

}

