package com.example.yikezhong.net;

import com.example.yikezhong.bean.HotBean;
import com.example.yikezhong.bean.HotLunBoBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created   by   Dewey .
 * 推荐热门
 */

public class Tui_ReMenApi {
    private Tui_ReMenApiService service;
    private static Tui_ReMenApi hotApi;

    public Tui_ReMenApi(Tui_ReMenApiService service) {
        this.service = service;
    }

    public  static Tui_ReMenApi getReMenApi(Tui_ReMenApiService service){
        if (hotApi == null){
            hotApi = new Tui_ReMenApi(service);
        }
        return hotApi;
    }

    public Observable<HotBean> getReMen(String token, String page ){
        return service.getTui_ReMen(token,page);
    }
}
