package com.example.yikezhong.module;

import com.example.yikezhong.net.Api;
import com.example.yikezhong.net.HotVideoApi;
import com.example.yikezhong.net.HotVideoApiService;
import com.example.yikezhong.net.Tui_HotApi;
import com.example.yikezhong.net.Tui_HotApiService;
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
                .addInterceptor(httpLoggingInterceptor);
    }

    @Provides     // 关键字，标明该方法提供依赖对象
    Tui_HotApi provideHotApi(OkHttpClient.Builder builder) {
        Tui_HotApiService service = new Retrofit.Builder()
                .baseUrl(Api.BASEURL)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(Tui_HotApiService.class);
        return Tui_HotApi.getHotApi(service);
    }
    @Provides     // 关键字，标明该方法提供依赖对象
    HotVideoApi provideHotVideoApi(OkHttpClient.Builder builder) {
        HotVideoApiService service = new Retrofit.Builder()
                .baseUrl(Api.BASEURL)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(HotVideoApiService.class);
        return HotVideoApi.getAdApi(service);
    }
}
