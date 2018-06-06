package com.example.yikezhong.net;

import com.example.yikezhong.bean.VideoDetailBean;

import io.reactivex.Observable;

/**
 * Created by lenovo on 2018/6/6.
 */

public class VideoDetailApi {
    private VideoDetailApiService service;
    private static VideoDetailApi videoDetailApi;

    public VideoDetailApi(VideoDetailApiService service) {
        this.service = service;
    }

    public  static VideoDetailApi getVideoDetailApi(VideoDetailApiService service){
        if (videoDetailApi == null){
            videoDetailApi = new VideoDetailApi(service);
        }
        return videoDetailApi;
    }

    public Observable<VideoDetailBean> getReMen(String token, String wid ){
        return service.getVideoDetail(token,wid);
    }
}
