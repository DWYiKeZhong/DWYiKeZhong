package com.example.yikezhong.ui.activity.VideoDetail.contract;

import com.example.yikezhong.bean.VideoDetailBean;
import com.example.yikezhong.ui.base.BaseContract;

/**
 * Created by lenovo on 2018/6/6.
 */

public interface VideoDetailContract {
    interface View extends BaseContract.BaseView {
        void getVideoDetailSuccess(VideoDetailBean Bean);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getVideoDetail(String token, String wid);
    }
}
