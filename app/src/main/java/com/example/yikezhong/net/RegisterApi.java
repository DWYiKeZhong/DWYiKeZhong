package com.example.yikezhong.net;

import com.example.yikezhong.bean.RegisterBean;

import io.reactivex.Observable;

/**
 * Created by lenovo on 2018/6/7.
 */

public class RegisterApi {
    private RegisterAPIService service;
    private static RegisterApi registerApi;

    public RegisterApi(RegisterAPIService service) {
        this.service = service;
    }

    public  static RegisterApi getRegisterApi(RegisterAPIService service){
        if (registerApi == null){
            registerApi = new RegisterApi(service);
        }
        return registerApi;
    }

    public Observable<RegisterBean> getRegister(String mobile, String password ){
        return service.getRegister(mobile,password);
    }
}
