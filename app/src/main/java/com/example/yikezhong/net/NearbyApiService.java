package com.example.yikezhong.net;

import com.example.yikezhong.bean.NearbyBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by lenovo on 2018/6/6.
 */

public interface NearbyApiService {
    @FormUrlEncoded
    @POST("quarter/getNearVideos")
    Observable<NearbyBean> getTui_ReMen(
            @Field("token") String token,
            @Field("latitude") Double latitude,
            @Field("longitude") Double longitude
    );
}
