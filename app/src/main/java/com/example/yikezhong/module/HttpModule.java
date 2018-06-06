package com.example.yikezhong.module;

import com.example.yikezhong.net.Api;
import com.example.yikezhong.net.DuanApi;
import com.example.yikezhong.net.DuanApiService;
import com.example.yikezhong.net.HotVideoApi;
import com.example.yikezhong.net.HotVideoApiService;
import com.example.yikezhong.net.Tui_GuanApi;
import com.example.yikezhong.net.Tui_GuanApiService;
import com.example.yikezhong.net.NearbyApi;
import com.example.yikezhong.net.NearbyApiService;
import com.example.yikezhong.net.Tui_LunBoApi;
import com.example.yikezhong.net.Tui_LunBoApiService;
import com.example.yikezhong.net.Tui_ReMenApi;
import com.example.yikezhong.net.Tui_ReMenApiService;
import com.example.yikezhong.net.VideoDetailApi;
import com.example.yikezhong.net.VideoDetailApiService;
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
 * Retrofit + RxJava2 + 自定义拦截器
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

    @Provides     // 推荐热门
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

    @Provides     // 热门视频
    HotVideoApi provideHotVideoApi(OkHttpClient.Builder builder) {
        HotVideoApiService service3 = new Retrofit.Builder()
                .baseUrl(Api.BASEURL)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(HotVideoApiService.class);
        return HotVideoApi.getAdApi(service3);
    }

    @Provides     // 推荐关注
    Tui_GuanApi provideGuanApi(OkHttpClient.Builder builder) {
        Tui_GuanApiService service4 = new Retrofit.Builder()
                .baseUrl(Api.BASEURL)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(Tui_GuanApiService.class);
        return Tui_GuanApi.getGuanApi(service4);
    }

    @Provides     // 附近视频
    NearbyApi provideNearbyApi(OkHttpClient.Builder builder) {
        NearbyApiService service4 = new Retrofit.Builder()
                .baseUrl(Api.BASEURL)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(NearbyApiService.class);
        return NearbyApi.getNearbyApi(service4);
    }

    @Provides     // 段子
    DuanApi provideDuanApi(OkHttpClient.Builder builder) {
        DuanApiService service4 = new Retrofit.Builder()
                .baseUrl(Api.BASEURL)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(DuanApiService.class);
        return DuanApi.getDuanApi(service4);
    }


    @Provides     // 视频详情
    VideoDetailApi provideVideoDetailApi(OkHttpClient.Builder builder) {
        VideoDetailApiService service5 = new Retrofit.Builder()
                .baseUrl(Api.BASEURL)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(VideoDetailApiService.class);
        return VideoDetailApi.getVideoDetailApi(service5);
    }
}
