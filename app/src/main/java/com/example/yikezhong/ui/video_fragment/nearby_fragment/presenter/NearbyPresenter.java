package com.example.yikezhong.ui.video_fragment.nearby_fragment.presenter;

import com.example.yikezhong.bean.NearbyBean;
import com.example.yikezhong.net.NearbyApi;
import com.example.yikezhong.ui.base.BasePresenter;
import com.example.yikezhong.ui.video_fragment.nearby_fragment.contract.NearbyContract;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lenovo on 2018/6/6.
 */

public class NearbyPresenter extends BasePresenter<NearbyContract.View> implements NearbyContract.Presenter {
    private NearbyApi nearbyApi;
    @Inject
    public NearbyPresenter(NearbyApi nearbyApi) {
        this.nearbyApi = nearbyApi;
    }

    @Override
    public void getNearby(String token, Double latitude , Double longitude) {
        nearbyApi.getReMen(token,latitude,longitude)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<NearbyBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NearbyBean hotVideoBean) {
                        if (mView!=null){
                            mView.getNearbySuccess(hotVideoBean);
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
