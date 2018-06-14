package com.example.yikezhong.ui.activity.register.presenter;

import com.example.yikezhong.bean.RegisterBean;
import com.example.yikezhong.net.RegisterApi;
import com.example.yikezhong.ui.activity.register.contract.RegisterContract;
import com.example.yikezhong.ui.base.BasePresenter;
import javax.inject.Inject;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lenovo on 2018/6/7.
 */

public class RegisterPresenter extends BasePresenter<RegisterContract.View> implements RegisterContract.Presenter{
    private RegisterApi registerApi;

    @Inject
    public RegisterPresenter(RegisterApi registerApi) {
        this.registerApi = registerApi;
    }


    @Override
    public void getRegister(String mobile, String password) {
        registerApi.getRegister(mobile,password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterBean registerBean) {
                        if (mView!=null){
                            mView.getRegisterSuccess(registerBean);
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
