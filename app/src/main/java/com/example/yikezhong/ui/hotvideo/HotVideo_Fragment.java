package com.example.yikezhong.ui.hotvideo;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.yikezhong.R;
import com.example.yikezhong.bean.HotVideoBean;
import com.example.yikezhong.component.DaggerHttpComponent;
import com.example.yikezhong.module.HttpModule;
import com.example.yikezhong.ui.base.BaseFragment;
import com.example.yikezhong.ui.hotvideo.contract.HotVideoContract;
import com.example.yikezhong.ui.hotvideo.presenter.HotVideoPresenter;

/**
 * Created by lenovo on 2018/6/5.
 */

public class HotVideo_Fragment extends BaseFragment<HotVideoPresenter> implements HotVideoContract.View {
    private RecyclerView rv;
    @Override
    public int getContentLayout() {
        return R.layout.hotvideo_fragment;
    }

    @Override
    public void inject() {
       /* DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);*/
    }

    @Override
    public void initView(View view) {
        rv=view.findViewById(R.id.Hot_Video_Rv);
        mPresenter.getHotVideo();
    }

    @Override
    public void getHotVideoSuccess(HotVideoBean adBean) {

    }
}
