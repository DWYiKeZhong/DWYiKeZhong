package com.example.yikezhong.net;

import com.example.yikezhong.bean.DuanBean;
import io.reactivex.Observable;

/**
 *  段子 列表
 */
public class DuanApi {
    private static DuanApi duanApi;
    private static DuanApiService service;

    private DuanApi(DuanApiService service) {
        this.service = service;
    }

    public static DuanApi getDuanApi(DuanApiService service) {
        if (duanApi == null) {
            duanApi = new DuanApi(service);
        }
        return duanApi;
    }

    public Observable<DuanBean> getDuan(String page) {
        return service.getDuan(page);
    }
}
