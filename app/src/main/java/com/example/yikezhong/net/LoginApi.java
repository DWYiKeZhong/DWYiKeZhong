package com.example.yikezhong.net;

import com.example.yikezhong.bean.LoginBean;

import io.reactivex.Observable;

/**
 * Created by lenovo on 2018/6/7.
 */

public class LoginApi {
    private LoginApiService service;
    private static LoginApi loginApi;

    public LoginApi(LoginApiService service) {
        this.service = service;
    }

    public  static LoginApi getLoginApi(LoginApiService service){
        if (loginApi == null){
            loginApi = new LoginApi(service);
        }
        return loginApi;
    }

    public Observable<LoginBean> getLogin(String mobile, String password ){
        return service.getLogin(mobile,password);
    }
}
