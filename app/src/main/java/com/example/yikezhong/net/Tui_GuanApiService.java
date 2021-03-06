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
     * 推荐 关注用户 数据 https://www.zhaoapi.cn/quarter/follow?source=android&appVersion=101&token=46FB809A1FFEE06DEDED783742F363CA&uid=4416&followId=123
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
     * 推荐 获取关注用户列表 数据 https://www.zhaoapi.cn/quarter/getFollowUsers?source=android&appVersion=101&token=46FB809A1FFEE06DEDED783742F363CA&uid=4416
     * @return
     */
    @FormUrlEncoded
    @POST("quarter/getFollowUsers")
    Observable<GuanListBean> getGuanList(
            @Field("token") String token,
            @Field("uid") String uid
    );

    /**
     * 收藏作品    https://www.zhaoapi.cn/quarter/addFavorite?source=android&appVersion=101&token=46FB809A1FFEE06DEDED783742F363CA&uid=12761&wid=4416
     * @param token
     * @param uid
     * @param wid
     * @return
     */
    @FormUrlEncoded
    @POST("quarter/addFavorite")
    Observable<GuanBean> getCollection(
            @Field("token") String token,
            @Field("uid") String uid,
            @Field("wid") String wid
    );

}
