package com.example.yikezhong.ui.activity.presenter;

import com.example.yikezhong.bean.UpdateHeaderBean;
import com.example.yikezhong.net.UpdateHeaderApi;
import com.example.yikezhong.ui.activity.contract.UpdateHeaderContract;
import com.example.yikezhong.ui.base.BasePresenter;
import java.io.File;
import javax.inject.Inject;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by lenovo on 2018/6/8.
 */

public class UpdatePresenter extends BasePresenter<UpdateHeaderContract.View> implements UpdateHeaderContract
        .Presenter {

    private UpdateHeaderApi updateHeaderApi;


    @Inject
    public UpdatePresenter(UpdateHeaderApi updateHeaderApi) {
        this.updateHeaderApi = updateHeaderApi;
    }

    @Override
    public void updateHeader(String uid, String filePath,String str) {
        int i = filePath.lastIndexOf("/");
        String fileName = filePath.substring(i + 1);
        RequestBody file = RequestBody.create(MediaType.parse("application/octet-stream"), new File(filePath));

        MediaType textType = MediaType.parse("text/plain");
        RequestBody u = RequestBody.create(textType, uid);
        RequestBody s = RequestBody.create(textType, str);
        MultipartBody.Part f = MultipartBody.Part.createFormData("file", fileName, file);
        updateHeaderApi.updateHeader(u, f,s)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<UpdateHeaderBean, String>() {
                    @Override
                    public String apply(UpdateHeaderBean baseBean) throws Exception {
                        return baseBean.getCode();
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                if (mView != null) {
                    mView.updateSuccess(s);
                }
            }
        });
    }
}
