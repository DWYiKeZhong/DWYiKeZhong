package com.example.yikezhong.ui.utils;

import java.io.IOException;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MyInterceptor implements Interceptor{
    private Response response;

    @Override
    public Response intercept(Chain chain) throws IOException {
        //1、获取用户使用的请求方式
        //1.1获取原始的请求
        Request orginalRequest = chain.request();
        //1.2获取请求方式
        if ("GET".equals(orginalRequest.method())) {
            //GET请求
            //获取原始的请求地址,然后添加参数，生成最终的请求地址
            HttpUrl url = orginalRequest.url()
                    .newBuilder()
                    .addQueryParameter("source", "android")//添加参数
                    .addQueryParameter("appVersion", "101")//添加参数
                    .build();
            Request request = orginalRequest.newBuilder().url(url).build();
            //发送拼接好参数的请求
            response = chain.proceed(request);

        } else if ("POST".equals(orginalRequest.method())) {

            //获取原始的请求体里的参数
            RequestBody body1 = orginalRequest.body();

            if (body1 instanceof FormBody){
                //POST请求
                FormBody.Builder builder = new FormBody.Builder();
                FormBody formBody= (FormBody) orginalRequest.body();
                //遍历原始的请求体里的参数
                for (int i = 0; i < formBody.size(); i++) {
                    builder.add(formBody.name(i), formBody.value(i));
                }
                //添加新参数
                builder.add("source", "android");
                builder.add("appVersion", "101");
                FormBody body = builder.build();
                //添加请求的参数
                Request request = orginalRequest.newBuilder()
                        .url(orginalRequest.url())
                        .post(body)
                        .build();
                response = chain.proceed(request);
            }else {
                response = chain.proceed(orginalRequest);
            }

        }
        return response;
    }
}
