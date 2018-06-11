package com.example.yikezhong.net;

import com.example.yikezhong.bean.GetUserVideosBean;
import io.reactivex.Observable;

/**
 *  获取某个用户的视频作品集
 */
public class GetUserVideosApi {
    private static GetUserVideosApi duanApi;
    private static GetUserVideosApiService service;

    private GetUserVideosApi(GetUserVideosApiService service) {
        this.service = service;
    }

    public static GetUserVideosApi getGetUserVideosApi(GetUserVideosApiService service) {
        if (duanApi == null) {
            duanApi = new GetUserVideosApi(service);
        }
        return duanApi;
    }

    public Observable<GetUserVideosBean> getUserVideos(String uid , String page) {
        return service.getUserVideos(uid,page);
    }
}
