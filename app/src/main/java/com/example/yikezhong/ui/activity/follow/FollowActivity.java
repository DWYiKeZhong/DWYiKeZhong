package com.example.yikezhong.ui.activity.follow;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.example.yikezhong.R;
import com.example.yikezhong.bean.GuanBean;
import com.example.yikezhong.bean.GuanListBean;
import com.example.yikezhong.bean.HotBean;
import com.example.yikezhong.bean.HotLunBoBean;
import com.example.yikezhong.component.DaggerHttpComponent;
import com.example.yikezhong.module.HttpModule;
import com.example.yikezhong.ui.activity.HomeActivity;
import com.example.yikezhong.ui.activity.follow.adapter.FollowAdapter;
import com.example.yikezhong.ui.base.BaseActivity;
import com.example.yikezhong.ui.shared.SharedPreferencesUtils;
import com.example.yikezhong.ui.tuijian_fragment.contract.TuiJianContract;
import com.example.yikezhong.ui.tuijian_fragment.presenter.TuiJianPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class FollowActivity extends BaseActivity<TuiJianPresenter> implements TuiJianContract.View {

    @BindView(R.id.follow_back)
    CircleImageView followBack;
    @BindView(R.id.follow_text)
    TextView followText;
    @BindView(R.id.follow_hot)
    TextView followHot;
    @BindView(R.id.follow_rv)
    RecyclerView followRv;
    private  int curren;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        curren= (Integer)SharedPreferencesUtils.getParam(FollowActivity.this,"position",0);
        if (curren== Configuration.UI_MODE_NIGHT_NO){
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        //获取uid
        String uid = (String) SharedPreferencesUtils.getParam(FollowActivity.this, "uid", "");
        String token = (String) SharedPreferencesUtils.getParam(FollowActivity.this, "token", "");
        if (uid != null && token != null) {
            mPresenter.getGuanListP(token, uid);
        }
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_follow;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }

    @Override
    public void getLunBoSuccess(HotLunBoBean lunBoBean) {

    }

    @Override
    public void getReMenSuccess(HotBean hotBean) {

    }

    @Override
    public void getGuanSuccess(GuanBean guanBean) {

    }

    @Override
    public void getGuanListSuccess(GuanListBean guanListBean) {
        followRv.setLayoutManager(new LinearLayoutManager(this));
        followRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        FollowAdapter adapter = new FollowAdapter(FollowActivity.this, guanListBean);
        followRv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @OnClick({R.id.follow_back, R.id.follow_text, R.id.follow_hot})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.follow_back:
                finish();
                break;
            case R.id.follow_text:
                break;
            case R.id.follow_hot:
                break;
        }
    }
}
