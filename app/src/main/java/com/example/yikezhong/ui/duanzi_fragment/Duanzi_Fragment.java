package com.example.yikezhong.ui.duanzi_fragment;

import android.os.Handler;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import com.example.yikezhong.R;
import com.example.yikezhong.bean.DuanBean;
import com.example.yikezhong.component.DaggerHttpComponent;
import com.example.yikezhong.module.HttpModule;
import com.example.yikezhong.ui.base.BaseFragment;
import com.example.yikezhong.ui.duanzi_fragment.adapter.DuanAdapter;
import com.example.yikezhong.ui.duanzi_fragment.contract.DuznZiContract;
import com.example.yikezhong.ui.duanzi_fragment.presenter.DuznZiPresenter;
import com.example.yikezhong.ui.utils.DialogUtil;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import java.util.ArrayList;
import java.util.List;

/**
 * 段子 列表
 */
public class Duanzi_Fragment extends BaseFragment<DuznZiPresenter> implements DuznZiContract.View {
    int page = 1;
    private Handler handler = new Handler();
    private DuanAdapter adapter;
    private XRecyclerView recyclerView;
    private SmartRefreshLayout duanzi_smart;

    @Override
    public int getContentLayout() {
        return R.layout.duan_fragment;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }

    @Override
    public void initView(View view) {
        duanzi_smart = view.findViewById(R.id.duanzi_smart);

        //添加参数
        recyclerView = view.findViewById(R.id.dzRecyclerview);
        List<DuanBean.DataBean> list = new ArrayList<>();
        mPresenter.getDuanP("1");
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        adapter = new DuanAdapter(getActivity(),list);
        recyclerView.setAdapter(adapter);

        //网络加载慢时，提示
        DialogUtil.showProgressDialog(getActivity(),"提示", "正在加载......");

        // Android 动态刷新 smart 的上拉刷新下拉加载更多
        duanzi_smart.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPresenter.getDuanP(Integer.toString(page++));
                    }
                },1500);
            }
        });
        duanzi_smart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        duanzi_smart.finishRefresh();
                    }
                },1500);
            }
        });
    }

    @Override
    public void getDuanSuccess(DuanBean duanBean) {
        duanzi_smart.finishLoadmore();  //在此提示下拉加载完毕

        if (adapter != null){
            adapter.addData(duanBean.getData());
            DialogUtil.hideProgressDialog();    //在此提示数据加载完毕，隐藏提示框
        }
    }

}
