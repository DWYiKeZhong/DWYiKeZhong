package com.example.yikezhong.net;

import com.example.yikezhong.bean.HotBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created   by   Dewey .
 * 推荐 关注
 */
public interface Tui_GuanApiService {

    /**
     * 推荐 关注 数据 https://www.zhaoapi.cn/quarter/getHotVideos?source=android&appVersion=101&token=46FB809A1FFEE06DEDED783742F363CA&page=1
     * @return
     */
    @FormUrlEncoded
    @POST("")
    Observable<HotBean> getGuan(
            @Field("token") String token
    );

}
