package com.example.yikezhong.net;

import com.example.yikezhong.bean.RegisterBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by lenovo on 2018/6/7.
 */

public interface RegisterAPIService {
    @FormUrlEncoded
    @POST("quarter/register")
    Observable<RegisterBean> getRegister(
            @Field("mobile") String mobile,
            @Field("password") String password
    );
}
