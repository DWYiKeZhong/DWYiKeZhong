package com.example.yikezhong.net;

import com.example.yikezhong.bean.GuanBean;
import com.example.yikezhong.bean.GuanListBean;
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

    public Observable<GuanBean> getGuan(String token, String uid, String followId){
        return service.getGuan(token,uid,followId);
    }

    public Observable<GuanListBean> getGuanList(String token, String uid){
        return service.getGuanList(token,uid);
    }

    public Observable<GuanBean> getCollection(String token, String uid, String followId){
        return service.getCollection(token,uid,followId);
    }
}
