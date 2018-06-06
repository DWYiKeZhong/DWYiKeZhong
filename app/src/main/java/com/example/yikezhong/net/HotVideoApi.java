package com.example.yikezhong.net;

import com.example.yikezhong.bean.HotVideoBean;

import io.reactivex.Observable;

/**
 * Created by lenovo on 2018/6/5.
 */

public class HotVideoApi {
    private static HotVideoApi adApi;
    private static HotVideoApiService adApiService;

    private HotVideoApi(HotVideoApiService adApiService) {
        this.adApiService = adApiService;
    }

    public static HotVideoApi getAdApi(HotVideoApiService adApiService) {
        if (adApi == null) {
            adApi = new HotVideoApi(adApiService);
        }
        return adApi;
    }

    public Observable<HotVideoBean> getAd() {
        return adApiService.GetHotVideo();
    }
}
