package com.example.yikezhong.ui.tuijian_fragment;

import android.os.Handler;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.example.yikezhong.R;
import com.example.yikezhong.bean.HotBean;
import com.example.yikezhong.bean.HotLunBoBean;
import com.example.yikezhong.component.DaggerHttpComponent;
import com.example.yikezhong.module.HttpModule;
import com.example.yikezhong.ui.base.BaseFragment;
import com.example.yikezhong.ui.tuijian_fragment.adapter.ReMenAdapter;
import com.example.yikezhong.ui.tuijian_fragment.contract.TuiJianContract;
import com.example.yikezhong.ui.tuijian_fragment.presenter.TuiJianPresenter;
import com.example.yikezhong.ui.utils.DialogUtil;
import com.example.yikezhong.ui.utils.GlideImageLoader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import java.util.ArrayList;
import java.util.List;

/**
 * Created   by    Dewey
 * 热门
 */
public class Tui_Hot_Fragment extends BaseFragment<TuiJianPresenter> implements TuiJianContract.View {
    int page = 1;
    private Banner banner;
    private Handler handler = new Handler();
    private ReMenAdapter adapter;
    private RecyclerView recyclerView;
    private SmartRefreshLayout tuijian_smart;

    @Override
    public int getContentLayout() {
        return R.layout.tui_hot_fragment;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }

    //初始化视图，加载数据
    @Override
    public void initView(View view) {
        tuijian_smart = view.findViewById(R.id.tuijian_smart);
        banner = (Banner) view.findViewById(R.id.banner);
        mPresenter.getLunBoP();

        //添加参数
        recyclerView = view.findViewById(R.id.recyclerView);
        List<HotBean.DataBean> list = new ArrayList<>();
        mPresenter.getReMenP("46FB809A1FFEE06DEDED783742F363CA","1");
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        adapter = new ReMenAdapter(getActivity(),list);
        recyclerView.setAdapter(adapter);

        //网络加载慢时，提示
        DialogUtil.showProgressDialog(getActivity(),"提示", "正在加载......");

        // Android 动态刷新 smart 的上拉刷新下拉加载更多
        tuijian_smart.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPresenter.getReMenP("46FB809A1FFEE06DEDED783742F363CA",Integer.toString(page++));
                    }
                },1500);
            }
        });
        tuijian_smart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tuijian_smart.finishRefresh();
                    }
                },1500);
            }
        });
    }

    @Override
    public void getLunBoSuccess(HotLunBoBean lunBoBean) {
        //添加图片的集合,进行banner轮播
        initBanner();
        List<String> image = new ArrayList<>();
        List<HotLunBoBean.DataBean> data = lunBoBean.getData();
        for (int i = 0; i < data.size(); i++) {
            image.add(data.get(i).getIcon());
            System.out.println("图片："+lunBoBean.getMsg()+data.toString()+image.toString());
        }
        banner.setImages(image);
        banner.start();
    }

    @Override
    public void getReMenSuccess(HotBean dataBeans) {
        tuijian_smart.finishLoadmore();  //在此提示下拉加载完毕

        if (adapter != null){
            adapter.addData(dataBeans.getData());
            DialogUtil.hideProgressDialog();     //在此提示数据加载完毕，隐藏提示框
        }
    }

    @Override
    public void getGuanSuccess(HotBean hotBean) {

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
}
