package com.example.yikezhong.net;

import com.example.yikezhong.bean.VideoDetailBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by lenovo on 2018/6/6.
 */

public interface VideoDetailApiService {
    @FormUrlEncoded
    @POST("quarter/getVideoDetail")
    Observable<VideoDetailBean> getVideoDetail(
            @Field("token") String token,
            @Field("wid") String wid
    );
}
