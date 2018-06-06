package com.example.yikezhong.ui.video_fragment.nearby_fragment.contract;

import com.example.yikezhong.bean.NearbyBean;
import com.example.yikezhong.ui.base.BaseContract;

/**
 * Created by lenovo on 2018/6/6.
 * 视频 附近
 */

public interface NearbyContract {
    interface View extends BaseContract.BaseView {
        void getNearbySuccess(NearbyBean bean);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getNearby(String token, Double latitude , Double longitude);
    }
}
