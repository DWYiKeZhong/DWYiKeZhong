package com.example.yikezhong.net;

import com.example.yikezhong.bean.HotLunBoBean;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created   by   Dewey .
 */

public interface Tui_HotApiService {
    @GET("quarter/getAd")
    Observable<HotLunBoBean> getLunBo();
}
