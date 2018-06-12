package com.example.yikezhong.ui.activity.contract;

import com.example.yikezhong.bean.GetUserVideos_DuanDetailBean;
import com.example.yikezhong.ui.base.BaseContract;

/**
 * 获取某个用户的视频作品集  段子详情
 */

public interface GetUserVideos_DuanDetailContract {
    interface View extends BaseContract.BaseView{
        void getUserVideos_DuanDetailSuccess(GetUserVideos_DuanDetailBean getUserVideosBean);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        void getUserVideos_DuanDetailP(String uid, String page);
    }
}
