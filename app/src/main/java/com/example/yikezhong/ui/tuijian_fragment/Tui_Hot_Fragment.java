package com.example.yikezhong.ui.tuijian_fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.yikezhong.R;
import com.example.yikezhong.ui.utils.GlideImageLoader;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created   by    Dewey
 * 热门
 */
public class Tui_Hot_Fragment extends Fragment {
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.recyclerView)
    XRecyclerView recyclerView;
    @BindView(R.id.tuijian_smart)
    SmartRefreshLayout tuijianSmart;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tui_hot_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);

        //添加图片的集合,进行banner轮播
        initBanner();
        List<Integer>  image = new ArrayList<>();
        image.add(R.drawable.ad1);
        image.add(R.drawable.ad2);
        image.add(R.drawable.ad3);
        image.add(R.drawable.ad4);
        banner.setImages(image);
        banner.start();


















        return view;
    }

    private void initBanner() {
        //设置banner样式...CIRCLE_INDICATOR_TITLE包含标题
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1800);
        //设置 小圆点 指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
