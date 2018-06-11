package com.example.yikezhong.ui.activity.presenter;

import com.example.yikezhong.bean.GetUserVideosBean;
import com.example.yikezhong.net.GetUserVideosApi;
import com.example.yikezhong.ui.activity.contract.GetUserVideosContract;
import com.example.yikezhong.ui.base.BasePresenter;
import javax.inject.Inject;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 *  获取某个用户的视频作品集
 */

public class GetUserVideosPresenter extends BasePresenter<GetUserVideosContract.View> implements GetUserVideosContract.Presenter {
    private GetUserVideosApi getUserVideosApi;

    @Inject
    public GetUserVideosPresenter(GetUserVideosApi getUserVideosApi) {
        this.getUserVideosApi = getUserVideosApi;
    }


    @Override
    public void getUserVideosP(String uid, String page) {
        getUserVideosApi.getUserVideos(uid,page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<GetUserVideosBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetUserVideosBean getUserVideosBean) {
                        if (mView != null){
                            mView.getUserVideosSuccess(getUserVideosBean);
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
