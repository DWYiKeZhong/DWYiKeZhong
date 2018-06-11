package com.example.yikezhong.ui.activity.Collection.presenter;

import com.example.yikezhong.bean.CollectionBean;
import com.example.yikezhong.net.CollectionApi;
import com.example.yikezhong.ui.activity.Collection.contract.CollectionCotract;
import com.example.yikezhong.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lenovo on 2018/6/11.
 */

public class CollectionPresenter extends BasePresenter<CollectionCotract.View> implements CollectionCotract.Presenter{
    private CollectionApi collectionApi;

    @Inject
    public CollectionPresenter(CollectionApi collectionApi) {
        this.collectionApi = collectionApi;
    }

    @Override
    public void getCollection(String uid,String token) {
        collectionApi.getCollection(uid,token)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<CollectionBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CollectionBean collectionBean) {
                        if (mView!=null){
                            mView.getCollectionSuccess(collectionBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
