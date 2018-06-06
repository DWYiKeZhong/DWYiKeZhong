package com.example.yikezhong.net;

import com.example.yikezhong.bean.HotBean;
import io.reactivex.Observable;

/**
 * Created   by   Dewey .
 * 推荐 关注
 */
public class Tui_GuanApi {
    private Tui_GuanApiService service;
    private static Tui_GuanApi hotApi;

    public Tui_GuanApi(Tui_GuanApiService service) {
        this.service = service;
    }

    public  static Tui_GuanApi getGuanApi(Tui_GuanApiService service){
        if (hotApi == null){
            hotApi = new Tui_GuanApi(service);
        }
        return hotApi;
    }

    public Observable<HotBean> getGuan(String token){
        return service.getGuan(token);
    }

}
