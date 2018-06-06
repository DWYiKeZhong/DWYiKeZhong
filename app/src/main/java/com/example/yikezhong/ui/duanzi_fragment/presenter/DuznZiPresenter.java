package com.example.yikezhong.ui.duanzi_fragment.presenter;

import com.example.yikezhong.bean.DuanBean;
import com.example.yikezhong.net.DuanApi;
import com.example.yikezhong.ui.base.BasePresenter;
import com.example.yikezhong.ui.duanzi_fragment.contract.DuznZiContract;
import javax.inject.Inject;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 段子 列表
 */
public class DuznZiPresenter extends BasePresenter<DuznZiContract.View> implements DuznZiContract.Presenter{
    private DuanApi duanApi;

    @Inject
    public DuznZiPresenter(DuanApi duanApi) {
        this.duanApi = duanApi;
    }

    @Override
    public void getDuanP(String page) {
        duanApi.getDuan(page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<DuanBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DuanBean duanBean) {
                        if (mView != null){
                            mView.getDuanSuccess(duanBean);
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
