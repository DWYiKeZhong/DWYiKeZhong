package com.example.yikezhong.component;

import com.example.yikezhong.module.HttpModule;
import com.example.yikezhong.ui.activity.FaBiaoDuznZiActivity;
import com.example.yikezhong.ui.activity.collection.CollectionActivity;
import com.example.yikezhong.ui.activity.HomeActivity;
import com.example.yikezhong.ui.activity.UserVideos_DuanDetailActivity;
import com.example.yikezhong.ui.activity.follow.FollowActivity;
import com.example.yikezhong.ui.activity.login.LoginActivity;
import com.example.yikezhong.ui.activity.register.RegisterActivity;
import com.example.yikezhong.ui.activity.search.SearchActivity;
import com.example.yikezhong.ui.activity.videodetail.VideoDetailActivity;
import com.example.yikezhong.ui.duanzi_fragment.Duanzi_Fragment;
import com.example.yikezhong.ui.tuijian_fragment.Tui_GuanContent_Fragment;
import com.example.yikezhong.ui.video_fragment.HotVideo_Fragment;
import com.example.yikezhong.ui.tuijian_fragment.Tui_Hot_Fragment;
import com.example.yikezhong.ui.video_fragment.nearby_fragment.Nearby_Fragment;
import dagger.Component;

/**
 * 注入内容
 */
@Component(modules = HttpModule.class)
public interface HttpComponent {
    void inject(Tui_Hot_Fragment tui_hot_fragment);

    void inject(Tui_GuanContent_Fragment tui_guanContent_fragment);

    void inject(HotVideo_Fragment hotVideoFragment);

    void inject(Nearby_Fragment nearbyFragment);

    void inject(VideoDetailActivity videoDetailActivity);

    void inject(Duanzi_Fragment duanzi_fragment);

    void inject(LoginActivity loginActivity);

    void inject(RegisterActivity registerActivity);

    void inject(HomeActivity homeActivity);

    void inject(UserVideos_DuanDetailActivity userActivity);

    void inject(FaBiaoDuznZiActivity faBiaoDuznZiActivity);

    void inject(FollowActivity followActivity);

    void inject(CollectionActivity collectionActivity);

    void inject(SearchActivity searchActivity);
}
