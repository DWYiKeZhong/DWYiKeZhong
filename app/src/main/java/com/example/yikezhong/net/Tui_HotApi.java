package com.example.yikezhong.net;

import com.example.yikezhong.bean.HotLunBoBean;

import io.reactivex.Observable;

/**
 * Created   by   Dewey .
 * 推荐热门
 */

public class Tui_HotApi {
    private Tui_HotApiService service;
    private static Tui_HotApi hotApi;

    public Tui_HotApi(Tui_HotApiService service) {
        this.service = service;
    }

    public  static Tui_HotApi getHotApi(Tui_HotApiService service){
        if (hotApi == null){
            hotApi = new Tui_HotApi(service);
        }
        return hotApi;
    }

    public Observable<HotLunBoBean> getLunBo(){
        return service.getLunBo();
    }
}
