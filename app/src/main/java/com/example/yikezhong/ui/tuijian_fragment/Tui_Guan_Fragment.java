package com.example.yikezhong.ui.tuijian_fragment;

import android.os.Handler;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.example.yikezhong.R;
import com.example.yikezhong.bean.GuanBean;
import com.example.yikezhong.bean.GuanListBean;
import com.example.yikezhong.bean.HotBean;
import com.example.yikezhong.bean.HotLunBoBean;
import com.example.yikezhong.component.DaggerHttpComponent;
import com.example.yikezhong.module.HttpModule;
import com.example.yikezhong.ui.base.BaseFragment;
import com.example.yikezhong.ui.shared.SharedPreferencesUtils;
import com.example.yikezhong.ui.tuijian_fragment.adapter.GuanAdapter;
import com.example.yikezhong.ui.tuijian_fragment.contract.TuiJianContract;
import com.example.yikezhong.ui.tuijian_fragment.presenter.TuiJianPresenter;
import com.example.yikezhong.ui.utils.DialogUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created   by    Dewey
 * 推荐 关注
 */
public class Tui_Guan_Fragment extends BaseFragment<TuiJianPresenter> implements TuiJianContract.View {
    private GuanAdapter adapter;
    private RecyclerView recyclerView;
    private Handler handler = new Handler();
    private SmartRefreshLayout tuijianSmart;
    private String token;
    private String uid;

    @Override
    public int getContentLayout() {
        return R.layout.tui_guan_fragment;
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
        recyclerView = view.findViewById(R.id.recyclerView);
        tuijianSmart = view.findViewById(R.id.tuijian_smart);

        //先刷新数据，显示关注的用户列表
        initRefresh();

            //再次请求数据，进行展示
            mPresenter.getGuanListP("46FB809A1FFEE06DEDED783742F363CA", "4416");
            LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(manager);
            recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
            adapter = new GuanAdapter(getActivity());


            //网络加载慢时，提示
            DialogUtil.showProgressDialog(getActivity(),"提示", "正在加载......");

    }

    // Android 动态刷新 smart 的上拉刷新下拉加载更多
    private void initRefresh() {
        tuijianSmart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tuijianSmart.finishRefresh();
                    }
                },1500);
            }
        });
        tuijianSmart.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {

                        //获取 登录时保存的 参数值 ，请求数据
                        token = (String) SharedPreferencesUtils.getParam(getActivity(),"token","46FB809A1FFEE06DEDED783742F363CA");
                        uid = (String) SharedPreferencesUtils.getParam(getActivity(),"uid","4416");
                        mPresenter.getGuanListP("46FB809A1FFEE06DEDED783742F363CA", "4416");
                    }
                },1500);
            }
        });
    }

    @Override
    public void getGuanListSuccess(GuanListBean guanListBean) {
        tuijianSmart.finishLoadmore();  //在此提示下拉加载完毕

        if (adapter != null){
            adapter.addData(guanListBean.getData());
            recyclerView.setAdapter(adapter);
            DialogUtil.hideProgressDialog();     //在此提示数据加载完毕，隐藏提示框
        }
    }

    @Override
    public void getGuanSuccess(GuanBean guanBean) {

    }
    @Override
    public void getLunBoSuccess(HotLunBoBean lunBoBean) {

    }
    @Override
    public void getReMenSuccess(HotBean hotBean) {

    }
}
