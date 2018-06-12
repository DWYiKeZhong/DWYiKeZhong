package com.example.yikezhong.ui.activity.Collection.contract;

import com.example.yikezhong.bean.CollectionBean;
import com.example.yikezhong.ui.base.BaseContract;

/**
 * Created by lenovo on 2018/6/11.
 */

public interface CollectionCotract {
    interface View extends BaseContract.BaseView{

        void getCollectionSuccess(CollectionBean collectionBean);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        void getCollection(String uid,String token);
    }
}
