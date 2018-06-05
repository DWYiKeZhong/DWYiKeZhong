package com.example.yikezhong.ui.hotvideo.presenter;

import com.example.yikezhong.bean.HotVideoBean;
import com.example.yikezhong.net.HotVideoApi;
import com.example.yikezhong.ui.base.BasePresenter;
import com.example.yikezhong.ui.hotvideo.contract.HotVideoContract;
import com.example.yikezhong.ui.hotvideo.view.HotVideoView;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lenovo on 2018/6/5.
 */

public class HotVideoPresenter  extends BasePresenter<HotVideoContract.View> implements HotVideoContract.Presenter {
    private HotVideoApi hotVideoApi;
    @Inject
    public HotVideoPresenter(HotVideoApi hotVideoApi) {
        this.hotVideoApi = hotVideoApi;
    }

    @Override
    public void getHotVideo() {
        hotVideoApi.getAd()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<HotVideoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HotVideoBean hotVideoBean) {
                        if (mView!=null){
                            mView.getHotVideoSuccess(hotVideoBean);
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
