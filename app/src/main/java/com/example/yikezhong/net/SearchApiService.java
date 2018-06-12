package com.example.yikezhong.net;

import com.example.yikezhong.bean.SearchBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by lenovo on 2018/6/12.
 */

public interface SearchApiService {
    @FormUrlEncoded
    @POST("quarter/searchFriends")
    Observable<SearchBean> getSearch(
            @Field("keywords") String keywords
    );
}
