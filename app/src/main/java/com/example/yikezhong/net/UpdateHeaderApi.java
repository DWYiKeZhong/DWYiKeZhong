package com.example.yikezhong.net;

import com.example.yikezhong.bean.UpdateHeaderBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by lenovo on 2018/6/8.
 */

public class UpdateHeaderApi {
    private static UpdateHeaderApi updateHeaderApi;
    private UpdateHeaderApiService updateHeaderApiService;

    private UpdateHeaderApi(UpdateHeaderApiService updateHeaderApiService) {
        this.updateHeaderApiService = updateHeaderApiService;
    }

    public static UpdateHeaderApi getUpdateHeaderApi(UpdateHeaderApiService updateHeaderApiService) {
        if (updateHeaderApi == null) {
            updateHeaderApi = new UpdateHeaderApi(updateHeaderApiService);
        }
        return updateHeaderApi;
    }

    public Observable<UpdateHeaderBean> updateHeader(RequestBody uid, MultipartBody.Part file,RequestBody str) {
        return updateHeaderApiService.updateHeader(uid, file,str);
    }
}