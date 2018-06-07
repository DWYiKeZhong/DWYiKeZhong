package com.example.yikezhong.ui.activity.login.contract;

import com.example.yikezhong.bean.LoginBean;
import com.example.yikezhong.ui.base.BaseContract;

/**
 * Created by lenovo on 2018/6/7.
 */

public interface LoginContract {
    interface View extends BaseContract.BaseView{

        void getLoginSuccess(LoginBean loginBean);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        void getLogin(String mobile,String password);
    }
}
