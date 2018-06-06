package com.example.yikezhong.net;

import com.example.yikezhong.bean.DuanBean;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 段子 列表
 */
public interface DuanApiService {
    /**
     * 段子 列表   https://www.zhaoapi.cn/quarter/getJokes?appVersion=101&source=android&page=1
     */
    @FormUrlEncoded
    @POST("quarter/getJokes")
    Observable<DuanBean> getDuan(
            @Field("page") String page
    );

}
