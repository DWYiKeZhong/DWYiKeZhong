package com.example.yikezhong.ui.activity.contract;

import com.example.yikezhong.bean.GetUserVideosBean;
import com.example.yikezhong.ui.base.BaseContract;

/**
 * 获取某个用户的视频作品集
 */

public interface GetUserVideosContract {
    interface View extends BaseContract.BaseView{
        void getUserVideosSuccess(GetUserVideosBean getUserVideosBean);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        void getUserVideosP(String uid, String page);
    }
}
