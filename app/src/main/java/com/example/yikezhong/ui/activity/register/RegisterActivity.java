package com.example.yikezhong.ui.activity.register;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.yikezhong.R;
import com.example.yikezhong.bean.RegisterBean;
import com.example.yikezhong.component.DaggerHttpComponent;
import com.example.yikezhong.module.HttpModule;
import com.example.yikezhong.ui.activity.register.contract.RegisterContract;
import com.example.yikezhong.ui.activity.register.presenter.RegisterPresenter;
import com.example.yikezhong.ui.base.BaseActivity;
import com.umeng.analytics.MobclickAgent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterContract.View {
    @BindView(R.id.register_back)
    ImageView registerBack;
    @BindView(R.id.register_register)
    TextView registerRegister;
    @BindView(R.id.register_name)
    EditText registerName;
    @BindView(R.id.register_paw)
    EditText registerPaw;
    @BindView(R.id.register)
    Button register;

    @Override
    public int getContentLayout() {
        return R.layout.activity_register;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }

    @Override
    public void getRegisterSuccess(RegisterBean registerBean) {
        Toast.makeText(RegisterActivity.this,registerBean.getMsg().toString(),Toast.LENGTH_SHORT).show();
        if ("注册成功".equals(registerBean.getMsg().toString())){
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.register_back, R.id.register_register, R.id.register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register_back:
                finish();
                break;
            case R.id.register_register:
                finish();
                break;
            case R.id.register:
                mPresenter.getRegister(registerName.getText().toString(),registerPaw.getText().toString());
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
