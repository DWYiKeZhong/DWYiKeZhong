package com.example.yikezhong.net;

import com.example.yikezhong.bean.GetUserVideosBean;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 获取某个用户的视频作品集
 */
public interface GetUserVideosApiService {
    /**
     *   https://www.zhaoapi.cn/quarter/getUserVideos?appVersion=101&source=android&uid=4416&page=1
     */
    @FormUrlEncoded
    @POST("quarter/getUserVideos")
    Observable<GetUserVideosBean> getUserVideos(
            @Field("uid") String uid,
            @Field("page") String page
    );

}
