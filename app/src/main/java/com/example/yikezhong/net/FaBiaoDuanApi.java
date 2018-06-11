package com.example.yikezhong.net;

import com.example.yikezhong.bean.FaBiaoDuanBean;
import io.reactivex.Observable;

/**
 *  发布段子
 */
public class FaBiaoDuanApi {
    private static FaBiaoDuanApi duanApi;
    private static FaBiaoDuanApiService service;

    private FaBiaoDuanApi(FaBiaoDuanApiService service) {
        this.service = service;
    }

    public static FaBiaoDuanApi getFaBiaoDuanApi(FaBiaoDuanApiService service) {
        if (duanApi == null) {
            duanApi = new FaBiaoDuanApi(service);
        }
        return duanApi;
    }

    public Observable<FaBiaoDuanBean> getFaBiaoDuan(String uid, String token, String content) {
        return service.getFaBiaoDuan(uid, token, content);
    }
}
