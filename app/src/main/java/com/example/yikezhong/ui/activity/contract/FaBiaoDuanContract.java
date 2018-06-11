package com.example.yikezhong.ui.activity.contract;

import com.example.yikezhong.bean.FaBiaoDuanBean;
import com.example.yikezhong.ui.base.BaseContract;

/**
 * 发布段子内容
 */
public interface FaBiaoDuanContract {
    interface View extends BaseContract.BaseView{
        void getFaBiaoDuanSuccess(FaBiaoDuanBean faBiaoDuanBean);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        void getFaBiaoDuanP(String uid, String token,String content);
    }
}
