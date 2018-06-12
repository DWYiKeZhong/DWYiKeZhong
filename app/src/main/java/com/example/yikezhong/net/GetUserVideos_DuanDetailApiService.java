package com.example.yikezhong.net;

import com.example.yikezhong.bean.GetUserVideos_DuanDetailBean;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 获取某个用户的视频作品集  段子详情
 */
public interface GetUserVideos_DuanDetailApiService {
    /**
     *   https://www.zhaoapi.cn/quarter/getUserVideos?appVersion=101&source=android&uid=4416&page=1
     */
    @FormUrlEncoded
    @POST("quarter/getUserVideos")
    Observable<GetUserVideos_DuanDetailBean> getUserVideos_DuanDetail(
            @Field("uid") String uid,
            @Field("page") String page
    );

}
