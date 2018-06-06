package com.example.yikezhong.net;

import com.example.yikezhong.bean.HotBean;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created   by   Dewey .
 * 热门
 */
public interface Tui_ReMenApiService {

    /**
     * 推荐 热门 数据 https://www.zhaoapi.cn/quarter/getHotVideos?source=android&appVersion=101&token=46FB809A1FFEE06DEDED783742F363CA&page=1
     * @return
     */
    @FormUrlEncoded
    @POST("quarter/getHotVideos")
    Observable<HotBean> getTui_ReMen(
            @Field("token") String token,
            @Field("page") String page
    );

}
