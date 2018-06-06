package com.example.yikezhong.net;


import com.example.yikezhong.bean.NearbyBean;

import io.reactivex.Observable;

/**
 * Created by lenovo on 2018/6/6.
 */

public class NearbyApi {
    private NearbyApiService service;
    private static NearbyApi nearbyApi;

    public NearbyApi(NearbyApiService service) {
        this.service = service;
    }

    public  static NearbyApi getNearbyApi(NearbyApiService service){
        if (nearbyApi == null){
            nearbyApi = new NearbyApi(service);
        }
        return nearbyApi;
    }

    public Observable<NearbyBean> getReMen(String token, Double latitude , Double longitude){
        return service.getTui_ReMen(token,latitude,longitude);
    }
}
