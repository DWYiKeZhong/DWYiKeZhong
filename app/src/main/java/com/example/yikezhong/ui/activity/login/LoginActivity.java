package com.example.yikezhong.ui.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.yikezhong.R;
import com.example.yikezhong.bean.LoginBean;
import com.example.yikezhong.component.DaggerHttpComponent;
import com.example.yikezhong.module.HttpModule;
import com.example.yikezhong.ui.activity.login.contract.LoginContract;
import com.example.yikezhong.ui.activity.login.presenter.LoginPresenter;
import com.example.yikezhong.ui.activity.register.RegisterActivity;
import com.example.yikezhong.ui.base.BaseActivity;
import com.example.yikezhong.ui.shared.SharedPreferencesUtils;
import com.umeng.analytics.MobclickAgent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//登录页面布局
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {
    @BindView(R.id.login_back)
    ImageView loginBack;
    @BindView(R.id.login_register)
    TextView loginRegister;
    @BindView(R.id.login_name)
    EditText loginName;
    @BindView(R.id.login_paw)
    EditText loginPaw;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.login_forget)
    TextView loginForget;

    @Override
    public int getContentLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }

    @Override
    public void getLoginSuccess(LoginBean loginBean) {
        Toast.makeText(LoginActivity.this,loginBean.getMsg().toString(),Toast.LENGTH_SHORT).show();
        Toast.makeText(LoginActivity.this,loginBean.getMsg().toString(),Toast.LENGTH_SHORT).show();
        if ("登录成功".equals(loginBean.getMsg().toString())){
            SharedPreferencesUtils.setParam(LoginActivity.this,"token",loginBean.getData().getToken()+"");
            SharedPreferencesUtils.setParam(LoginActivity.this,"uid",loginBean.getData().getUid()+"");
            SharedPreferencesUtils.setParam(LoginActivity.this,"name",loginBean.getData().getNickname()+"");
            SharedPreferencesUtils.setParam(LoginActivity.this,"iocn",loginBean.getData().getIcon()+"");
            finish();
            LoginActivity.this.finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

    }

    @OnClick({R.id.login_back, R.id.login_register, R.id.login, R.id.login_forget})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_back:
                finish();
                break;
            case R.id.login_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.login:
                mPresenter.getLogin(loginName.getText().toString(),loginPaw.getText().toString());
                break;
            case R.id.login_forget:
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
