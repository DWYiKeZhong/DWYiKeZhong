package com.example.yikezhong.module;

import com.example.yikezhong.net.Api;
import com.example.yikezhong.net.Tui_LunBoApi;
import com.example.yikezhong.net.Tui_LunBoApiService;
import com.example.yikezhong.net.Tui_ReMenApi;
import com.example.yikezhong.net.Tui_ReMenApiService;
import com.example.yikezhong.ui.utils.MyInterceptor;

import java.util.concurrent.TimeUnit;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created   by   Dewey .
 */
@Module      //提供依赖对象的实例
public class HttpModule {
    @Provides
    OkHttpClient.Builder provideOkHttpClientBuilder() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(new MyInterceptor());
    }

    @Provides     // 轮播图
    Tui_LunBoApi provideHotApi(OkHttpClient.Builder builder) {
        Tui_LunBoApiService service1 = new Retrofit.Builder()
                .baseUrl(Api.BASEURL)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(Tui_LunBoApiService.class);
        return Tui_LunBoApi.getHotApi(service1);
    }

    @Provides     // 热门
    Tui_ReMenApi provideReMenApi(OkHttpClient.Builder builder) {
        Tui_ReMenApiService service2 = new Retrofit.Builder()
                .baseUrl(Api.BASEURL)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(Tui_ReMenApiService.class);
        return Tui_ReMenApi.getReMenApi(service2);
    }
}
