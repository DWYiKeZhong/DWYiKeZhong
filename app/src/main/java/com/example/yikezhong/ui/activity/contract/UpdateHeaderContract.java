package com.example.yikezhong.ui.activity.contract;

import com.example.yikezhong.ui.base.BaseContract;

/**
 * Created by lenovo on 2018/6/8.
 */

public interface UpdateHeaderContract {
    interface View extends BaseContract.BaseView{
        void updateSuccess(String code);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        void updateHeader(String uid, String filePath,String str);
    }
}
