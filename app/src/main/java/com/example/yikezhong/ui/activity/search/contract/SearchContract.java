package com.example.yikezhong.ui.activity.search.contract;

import com.example.yikezhong.bean.SearchBean;
import com.example.yikezhong.ui.base.BaseContract;

/**
 * Created by lenovo on 2018/6/12.
 */
public interface SearchContract {
    interface View extends BaseContract.BaseView{

        void getSearchSuccess(SearchBean searchBean);
    }

    interface Presenter extends BaseContract.BasePresenter<SearchContract.View>{
        void getSearch(String keywords);
    }
}
