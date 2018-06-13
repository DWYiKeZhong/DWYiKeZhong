package com.example.yikezhong.ui.tuijian_fragment.presenter;

import com.example.yikezhong.bean.GuanListBean;
import com.example.yikezhong.bean.HotBean;
import com.example.yikezhong.bean.HotLunBoBean;
import com.example.yikezhong.net.Tui_GuanApi;
import com.example.yikezhong.net.Tui_LunBoApi;
import com.example.yikezhong.net.Tui_ReMenApi;
import com.example.yikezhong.ui.base.BasePresenter;
import com.example.yikezhong.ui.tuijian_fragment.contract.TuiJianContract;
import javax.inject.Inject;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created   by   Dewey .
 * 推荐
 */
public class TuiJianPresenter  extends BasePresenter<TuiJianContract.View> implements TuiJianContract.Presenter{
    private Tui_LunBoApi hotApi;
    private Tui_ReMenApi reMenApi;
    private Tui_GuanApi guanApi;

    @Inject
    public TuiJianPresenter(Tui_LunBoApi hotApi, Tui_ReMenApi reMenApi, Tui_GuanApi guanApi) {
        this.hotApi = hotApi;
        this.reMenApi = reMenApi;
        this.guanApi = guanApi;
    }

    @Override
    public void getLunBoP() {
        hotApi.getLunBo()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<HotLunBoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HotLunBoBean lunBoBean) {
                        if (mView != null){
                            mView.getLunBoSuccess(lunBoBean);
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

    @Override
    public void getReMenP(String token, String page) {
        reMenApi.getReMen(token,page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<HotBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HotBean dataBeans) {
                        if (mView != null){
                            mView.getReMenSuccess(dataBeans);
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

    @Override
    public void getGuanListP(String token, String uid) {
        guanApi.getGuanList(token, uid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<GuanListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GuanListBean guanListBean) {
                        if (mView != null){
                            mView.getGuanListSuccess(guanListBean);
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
