package com.example.yikezhong.ui.activity.login.presenter;

import com.example.yikezhong.bean.LoginBean;
import com.example.yikezhong.net.LoginApi;
import com.example.yikezhong.ui.activity.login.contract.LoginContract;
import com.example.yikezhong.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lenovo on 2018/6/7.
 */

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter{
    private LoginApi loginApi;

    @Inject
    public LoginPresenter(LoginApi loginApi) {
        this.loginApi = loginApi;
    }

    @Override
    public void getLogin(String mobile, String password) {
        loginApi.getLogin(mobile,password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        if (mView!=null){
                            mView.getLoginSuccess(loginBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
