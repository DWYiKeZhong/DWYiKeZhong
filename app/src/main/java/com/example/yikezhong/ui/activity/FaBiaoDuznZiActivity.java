package com.example.yikezhong.ui.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.yikezhong.R;
import com.example.yikezhong.bean.FaBiaoDuanBean;
import com.example.yikezhong.component.DaggerHttpComponent;
import com.example.yikezhong.module.HttpModule;
import com.example.yikezhong.ui.activity.contract.FaBiaoDuanContract;
import com.example.yikezhong.ui.activity.presenter.FaBiaoDuanPresenter;
import com.example.yikezhong.ui.base.BaseActivity;
import com.example.yikezhong.ui.shared.SharedPreferencesUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//发布段子内容
public class FaBiaoDuznZiActivity extends BaseActivity<FaBiaoDuanPresenter> implements FaBiaoDuanContract.View {
    @BindView(R.id.qx_text)
    TextView qxText;
    @BindView(R.id.fabiaoText)
    TextView fabiaoText;
    @BindView(R.id.duanzi_id)
    EditText duanziId;
    @BindView(R.id.add)
    ImageView add;
    @BindView(R.id.imageView)
    ImageView imageView;
    private  int curren;
    @Override
    public int getContentLayout() {
        return R.layout.activity_fabiao_duznzi;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }


    @Override
    public void getFaBiaoDuanSuccess(FaBiaoDuanBean faBiaoDuanBean) {
        if (faBiaoDuanBean.getCode().equals("0")){           //发表成功
            Toast.makeText(FaBiaoDuznZiActivity.this, faBiaoDuanBean.getMsg(), Toast.LENGTH_SHORT).show();
        }else if (faBiaoDuanBean.getCode().equals("1")){     //发表失败
            Toast.makeText(FaBiaoDuznZiActivity.this, faBiaoDuanBean.getMsg(), Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        curren= (Integer)SharedPreferencesUtils.getParam(FaBiaoDuznZiActivity.this,"position",0);
        if (curren== Configuration.UI_MODE_NIGHT_NO){
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    @OnClick({R.id.qx_text, R.id.fabiaoText, R.id.add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.qx_text:
                Toast.makeText(FaBiaoDuznZiActivity.this,"取消！",Toast.LENGTH_SHORT).show();

                break;

            case R.id.fabiaoText:       //发表段子内容  "4416"      "46FB809A1FFEE06DEDED783742F363CA"
                String content = duanziId.getText().toString();
                String uid = (String) SharedPreferencesUtils.getParam(FaBiaoDuznZiActivity.this, "uid", "");
                String token = (String) SharedPreferencesUtils.getParam(FaBiaoDuznZiActivity.this, "token", "");

                if (uid == null || token == null || uid.length() <= 0 || token.length() <= 0){
                    Toast.makeText(FaBiaoDuznZiActivity.this,"请登录后再发表哦！",Toast.LENGTH_SHORT).show();
                }else {
                    mPresenter.getFaBiaoDuanP(uid,token,content);
                }


                break;

            case R.id.add:
                break;

            default:
                break;
        }
    }
}
