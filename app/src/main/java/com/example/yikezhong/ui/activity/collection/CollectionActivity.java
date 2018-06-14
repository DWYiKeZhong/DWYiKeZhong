package com.example.yikezhong.ui.activity.collection;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.yikezhong.R;
import com.example.yikezhong.bean.CollectionBean;
import com.example.yikezhong.component.DaggerHttpComponent;
import com.example.yikezhong.module.HttpModule;
import com.example.yikezhong.ui.activity.HomeActivity;
import com.example.yikezhong.ui.activity.collection.adapter.CollectionAdapter;
import com.example.yikezhong.ui.activity.collection.contract.CollectionCotract;
import com.example.yikezhong.ui.activity.collection.presenter.CollectionPresenter;
import com.example.yikezhong.ui.base.BaseActivity;
import com.example.yikezhong.ui.shared.SharedPreferencesUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

//收藏
public class CollectionActivity extends BaseActivity<CollectionPresenter> implements CollectionCotract.View {

    @BindView(R.id.collection_back)
    CircleImageView collectionBack;
    @BindView(R.id.collection_text)
    TextView collectionText;
    @BindView(R.id.collection_hot)
    TextView collectionHot;
    @BindView(R.id.collection_rl)
    RelativeLayout collectionRl;
    @BindView(R.id.follow_rv)
    RecyclerView followRv;
    private CollectionAdapter adapter;
    private  int curren;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        curren= (Integer)SharedPreferencesUtils.getParam(CollectionActivity.this,"position",0);
        if (curren== Configuration.UI_MODE_NIGHT_NO){
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        mPresenter.getCollection((String) SharedPreferencesUtils.getParam(CollectionActivity.this,"uid",""),(String) SharedPreferencesUtils.getParam(CollectionActivity.this,"token",""));
        LinearLayoutManager manager = new LinearLayoutManager(CollectionActivity.this, LinearLayoutManager.VERTICAL, false);
        followRv.setLayoutManager(manager);
        followRv.addItemDecoration(new DividerItemDecoration(CollectionActivity.this, DividerItemDecoration.VERTICAL));
        adapter = new CollectionAdapter(CollectionActivity.this);
        followRv.setAdapter(adapter);
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_collection;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }

    @Override
    public void getCollectionSuccess(CollectionBean collectionBean) {
        if (adapter!=null){
            adapter.addData(collectionBean.getData());
        }

    }

    @OnClick({R.id.collection_back, R.id.collection_hot})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.collection_back:
                finish();
                break;
            case R.id.collection_hot:
                break;
        }
    }
}
