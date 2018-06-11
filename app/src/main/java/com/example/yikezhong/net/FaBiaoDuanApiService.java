package com.example.yikezhong.net;

import com.example.yikezhong.bean.FaBiaoDuanBean;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 发布段子
 */
public interface FaBiaoDuanApiService {
    /**
     *   https://www.zhaoapi.cn/quarter/publishJoke?appVersion=101&source=android&token=46FB809A1FFEE06DEDED783742F363CA&uid=4416&content=%E6%98%8E%E5%A4%A9%E9%9D%A2%E8%AF%95%E4%BA%86
     */
    @FormUrlEncoded
    @POST("quarter/publishJoke")
    Observable<FaBiaoDuanBean> getFaBiaoDuan(
            @Field("uid") String uid,
            @Field("token") String token,
            @Field("content") String content
    );

}
