package com.example.yikezhong.ui.activity.presenter;

import com.example.yikezhong.bean.GetUserVideos_DuanDetailBean;
import com.example.yikezhong.net.GetUserVideos_DuanDetailApi;
import com.example.yikezhong.ui.activity.contract.GetUserVideos_DuanDetailContract;
import com.example.yikezhong.ui.base.BasePresenter;
import javax.inject.Inject;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 *  获取某个用户的视频作品集  段子详情
 */

public class GetUserVideos_DuanDetailPresenter extends BasePresenter<GetUserVideos_DuanDetailContract.View> implements GetUserVideos_DuanDetailContract.Presenter {
    private GetUserVideos_DuanDetailApi getUserVideosApi;

    @Inject
    public GetUserVideos_DuanDetailPresenter(GetUserVideos_DuanDetailApi getUserVideosApi) {
        this.getUserVideosApi = getUserVideosApi;
    }


    @Override
    public void getUserVideos_DuanDetailP(String uid, String page) {
        getUserVideosApi.getUserVideos_DuanDetail(uid,page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<GetUserVideos_DuanDetailBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetUserVideos_DuanDetailBean getUserVideosBean) {
                        if (mView != null){
                            mView.getUserVideos_DuanDetailSuccess(getUserVideosBean);
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
