package com.example.yikezhong.ui.activity.search.presenter;

import com.example.yikezhong.bean.SearchBean;
import com.example.yikezhong.net.SearchApi;
import com.example.yikezhong.ui.activity.search.contract.SearchContract;
import com.example.yikezhong.ui.base.BasePresenter;
import javax.inject.Inject;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lenovo on 2018/6/12.
 */

public class SearchPresenter extends BasePresenter<SearchContract.View> implements SearchContract.Presenter{
    private SearchApi searchApi;

    @Inject
    public SearchPresenter(SearchApi searchApi) {
        this.searchApi = searchApi;
    }

    @Override
    public void getSearch(String keywords) {
        searchApi.getSearch(keywords)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<SearchBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SearchBean searchBean) {
                        if (mView!=null){
                            mView.getSearchSuccess(searchBean);
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
