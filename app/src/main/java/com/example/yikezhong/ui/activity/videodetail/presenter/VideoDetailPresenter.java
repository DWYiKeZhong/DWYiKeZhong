package com.example.yikezhong.ui.activity.videodetail.presenter;

import com.example.yikezhong.bean.VideoDetailBean;
import com.example.yikezhong.net.VideoDetailApi;
import com.example.yikezhong.ui.activity.videodetail.contract.VideoDetailContract;
import com.example.yikezhong.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lenovo on 2018/6/6.
 */

public class VideoDetailPresenter extends BasePresenter<VideoDetailContract.View> implements VideoDetailContract.Presenter{
    private VideoDetailApi videoDetailApi;

    @Inject
    public VideoDetailPresenter(VideoDetailApi videoDetailApi) {
        this.videoDetailApi = videoDetailApi;
    }

    @Override
    public void getVideoDetail(String token, String wid) {
        videoDetailApi.getReMen(token,wid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<VideoDetailBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(VideoDetailBean videoDetailBean) {
                        if (mView!=null){
                            mView.getVideoDetailSuccess(videoDetailBean);
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
