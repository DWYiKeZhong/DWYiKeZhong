package com.example.yikezhong.net;

import com.example.yikezhong.bean.CollectionBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by lenovo on 2018/6/11.
 */

public interface CollectionApiService {
    @FormUrlEncoded
    @POST("quarter/getFavorites")
    Observable<CollectionBean> getCollection(
            @Field("uid") String uid,
            @Field("token") String token
    );
}
