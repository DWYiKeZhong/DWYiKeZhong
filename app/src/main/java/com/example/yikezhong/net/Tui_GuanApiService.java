package com.example.yikezhong.net;

import com.example.yikezhong.bean.GuanBean;
import com.example.yikezhong.bean.GuanListBean;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created   by   Dewey .
 * 推荐 关注
 */
public interface Tui_GuanApiService {

    /**
     * 推荐 关注用户 数据 https://www.zhaoapi.cn/quarter/follow?source=android&appVersion=101&token=E3E7D261D59EC38EDDD000DB09CA42D4&uid=12761&followId=123
     * @return
     */
    @FormUrlEncoded
    @POST("quarter/follow")
    Observable<GuanBean> getGuan(
            @Field("token") String token,
            @Field("uid") String uid,
            @Field("followId") String followId
    );

    /**
     * 推荐 获取关注用户列表 数据 https://www.zhaoapi.cn/quarter/getFollowUsers?source=android&appVersion=101&token=E3E7D261D59EC38EDDD000DB09CA42D4&uid=12761
     * @return
     */
    @FormUrlEncoded
    @POST("quarter/getFollowUsers")
    Observable<GuanListBean> getGuanList(
            @Field("token") String token,
            @Field("uid") String uid
    );

}
