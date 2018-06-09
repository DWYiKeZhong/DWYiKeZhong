package com.example.yikezhong.ui.activity.register.contract;

import com.example.yikezhong.bean.RegisterBean;
import com.example.yikezhong.ui.activity.login.contract.LoginContract;
import com.example.yikezhong.ui.base.BaseContract;

/**
 * Created by lenovo on 2018/6/7.
 */

public interface RegisterContract {
    interface View extends BaseContract.BaseView{

        void getRegisterSuccess(RegisterBean registerBean);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        void getRegister(String mobile,String password);
    }
}
