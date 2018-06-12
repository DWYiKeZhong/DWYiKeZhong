package com.example.yikezhong.net;

import com.example.yikezhong.bean.GetUserVideos_DuanDetailBean;
import io.reactivex.Observable;

/**
 *  获取某个用户的视频作品集  段子详情
 */
public class GetUserVideos_DuanDetailApi {
    private static GetUserVideos_DuanDetailApi duanApi;
    private static GetUserVideos_DuanDetailApiService service;

    private GetUserVideos_DuanDetailApi(GetUserVideos_DuanDetailApiService service) {
        this.service = service;
    }

    public static GetUserVideos_DuanDetailApi getGetUserVideos_DuanDetailApi(GetUserVideos_DuanDetailApiService service) {
        if (duanApi == null) {
            duanApi = new GetUserVideos_DuanDetailApi(service);
        }
        return duanApi;
    }

    public Observable<GetUserVideos_DuanDetailBean> getUserVideos_DuanDetail(String uid , String page) {
        return service.getUserVideos_DuanDetail(uid,page);
    }
}
