package com.example.yikezhong.ui.activity.search;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.yikezhong.R;
import com.example.yikezhong.bean.SearchBean;
import com.example.yikezhong.component.DaggerHttpComponent;
import com.example.yikezhong.module.HttpModule;
import com.example.yikezhong.ui.activity.search.adapter.SearchAdapter;
import com.example.yikezhong.ui.activity.search.contract.SearchContract;
import com.example.yikezhong.ui.activity.search.presenter.SearchPresenter;
import com.example.yikezhong.ui.base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

//搜索钟友
public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchContract.View {

    @BindView(R.id.search_menu)
    CircleImageView searchMenu;
    @BindView(R.id.search_name)
    EditText searchName;
    @BindView(R.id.search_search)
    Button searchSearch;
    @BindView(R.id.search_rv)
    RecyclerView searchRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_search;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }

    @Override
    public void getSearchSuccess(SearchBean searchBean) {
        searchRv.setLayoutManager(new LinearLayoutManager(this));
        searchRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        SearchAdapter adapter=new SearchAdapter(SearchActivity.this,searchBean);
        searchRv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @OnClick({R.id.search_menu, R.id.search_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_menu:
                finish();
                break;
            case R.id.search_search:
                mPresenter.getSearch(searchName.getText().toString());
                break;
        }
    }
}
