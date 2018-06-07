package com.example.yikezhong.net;

import com.example.yikezhong.bean.LoginBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by lenovo on 2018/6/7.
 */

public interface LoginApiService {
    @FormUrlEncoded
    @POST("user/login")
    Observable<LoginBean> getLogin(
            @Field("mobile") String mobile,
            @Field("password") String password
    );
}
