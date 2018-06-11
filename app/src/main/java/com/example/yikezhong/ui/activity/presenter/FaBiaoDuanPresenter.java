package com.example.yikezhong.ui.activity.presenter;

import com.example.yikezhong.bean.FaBiaoDuanBean;
import com.example.yikezhong.net.FaBiaoDuanApi;
import com.example.yikezhong.ui.activity.contract.FaBiaoDuanContract;
import com.example.yikezhong.ui.base.BasePresenter;
import javax.inject.Inject;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 发布段子内容
 */
public class FaBiaoDuanPresenter extends BasePresenter<FaBiaoDuanContract.View> implements FaBiaoDuanContract
        .Presenter {

    private FaBiaoDuanApi faBiaoDuanApi;

    @Inject
    public FaBiaoDuanPresenter(FaBiaoDuanApi faBiaoDuanApi) {
        this.faBiaoDuanApi = faBiaoDuanApi;
    }


    @Override
    public void getFaBiaoDuanP(String uid, String token, String content) {
        faBiaoDuanApi.getFaBiaoDuan(uid,  token,  content)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<FaBiaoDuanBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FaBiaoDuanBean faBiaoDuanBean) {
                        if (mView != null){
                            mView.getFaBiaoDuanSuccess(faBiaoDuanBean);
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
