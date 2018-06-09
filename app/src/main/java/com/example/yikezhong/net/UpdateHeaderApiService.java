package com.example.yikezhong.net;

import com.example.yikezhong.bean.UpdateHeaderBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by lenovo on 2018/6/8.
 */

public interface UpdateHeaderApiService {
    @Multipart
    @POST("file/upload")
    Observable<UpdateHeaderBean> updateHeader(@Part("uid") RequestBody uid, @Part MultipartBody.Part file,@Part("token") RequestBody str);
}
