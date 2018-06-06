package com.example.yikezhong.ui.duanzi_fragment.contract;

import com.example.yikezhong.bean.DuanBean;
import com.example.yikezhong.ui.base.BaseContract;

/**
 * 段子 列表
 */
public interface DuznZiContract {
    interface View extends BaseContract.BaseView{
        void getDuanSuccess(DuanBean duanBean);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        void getDuanP(String page);
    }
}
