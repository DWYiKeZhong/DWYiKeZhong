package com.example.yikezhong.net;

import com.example.yikezhong.bean.HotBean;
import com.example.yikezhong.bean.HotLunBoBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created   by   Dewey .
 * 推荐热门
 */

public class Tui_LunBoApi {
    private Tui_LunBoApiService service;
    private static Tui_LunBoApi hotApi;

    public Tui_LunBoApi(Tui_LunBoApiService service) {
        this.service = service;
    }

    public  static Tui_LunBoApi getHotApi(Tui_LunBoApiService service){
        if (hotApi == null){
            hotApi = new Tui_LunBoApi(service);
        }
        return hotApi;
    }

    public Observable<HotLunBoBean> getLunBo(){
        return service.getLunBo();
    }

}
