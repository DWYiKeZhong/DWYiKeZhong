package com.example.yikezhong.ui.tuijian_fragment.contract;

import com.example.yikezhong.bean.GuanBean;
import com.example.yikezhong.bean.GuanListBean;
import com.example.yikezhong.bean.HotBean;
import com.example.yikezhong.bean.HotLunBoBean;
import com.example.yikezhong.ui.base.BaseContract;

/**
 * Created   by   Dewey .
 * 推荐 轮播 热门 关注
 */
public interface TuiJianContract {
    interface View extends BaseContract.BaseView{
        void getLunBoSuccess(HotLunBoBean lunBoBean);

        void getReMenSuccess(HotBean hotBean);

        void getGuanSuccess(GuanBean guanBean);

        void getGuanListSuccess(GuanListBean guanListBean);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        void getLunBoP();
        void getReMenP(String token,String page);
        void getGuanListP(String token,String uid);
    }
}
