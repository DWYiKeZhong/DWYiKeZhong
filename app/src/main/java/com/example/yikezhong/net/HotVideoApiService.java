package com.example.yikezhong.net;

import com.example.yikezhong.bean.HotVideoBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by lenovo on 2018/6/5.
 */

public interface HotVideoApiService {
    @GET("quarter/getHotVideos?token=100&source=android&appVersion=101")
    Observable<HotVideoBean> GetHotVideo();

}
