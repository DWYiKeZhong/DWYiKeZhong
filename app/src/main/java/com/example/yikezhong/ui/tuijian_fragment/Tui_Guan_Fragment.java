package com.example.yikezhong.ui.tuijian_fragment;

import android.view.View;
import com.example.yikezhong.R;
import com.example.yikezhong.bean.GuanBean;
import com.example.yikezhong.bean.GuanListBean;
import com.example.yikezhong.bean.HotBean;
import com.example.yikezhong.bean.HotLunBoBean;
import com.example.yikezhong.component.DaggerHttpComponent;
import com.example.yikezhong.module.HttpModule;
import com.example.yikezhong.ui.base.BaseFragment;
import com.example.yikezhong.ui.tuijian_fragment.contract.TuiJianContract;
import com.example.yikezhong.ui.tuijian_fragment.presenter.TuiJianPresenter;

/**
 * Created   by    Dewey
 * 推荐 关注
 */
public class Tui_Guan_Fragment extends BaseFragment<TuiJianPresenter> implements TuiJianContract.View {

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

    }

    @Override
    public void getGuanSuccess(GuanBean guanBean) {

    }

    @Override
    public void getGuanListSuccess(GuanListBean guanListBean) {

    }

    @Override
    public void getLunBoSuccess(HotLunBoBean lunBoBean) {

    }
    @Override
    public void getReMenSuccess(HotBean hotBean) {

    }
}
