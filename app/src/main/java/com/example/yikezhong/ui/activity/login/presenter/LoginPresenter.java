package com.example.yikezhong.ui.activity.login.presenter;

/**
 * Created by lenovo on 2018/6/7.
 */

public class LoginPresenter{}/* extends BasePresenter<VideoDetailContract.View> implements VideoDetailContract.Presenter{
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
}*/
