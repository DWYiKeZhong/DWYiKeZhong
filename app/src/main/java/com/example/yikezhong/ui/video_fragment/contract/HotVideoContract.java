package com.example.yikezhong.ui.video_fragment.contract;

import com.example.yikezhong.bean.HotVideoBean;
import com.example.yikezhong.ui.base.BaseContract;

/**
 * Created by lenovo on 2018/6/5.
 * 视频 热点
 */
public interface HotVideoContract {
    interface View extends BaseContract.BaseView {
        void getHotVideoSuccess(HotVideoBean adBean);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getHotVideo();
    }
}
