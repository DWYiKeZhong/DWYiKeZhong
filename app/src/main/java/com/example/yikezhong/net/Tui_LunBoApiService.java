package com.example.yikezhong.net;

import com.example.yikezhong.bean.HotLunBoBean;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created   by   Dewey .
 */

public interface Tui_LunBoApiService {
    /**
     * 推荐 热门轮播图
     * @return
     */
    @GET("quarter/getAd")
    Observable<HotLunBoBean> getLunBo();

}
