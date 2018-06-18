package com.example.yikezhong.ui.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.yikezhong.R;
import com.example.yikezhong.ui.activity.upload.UpLoadActivity;
import com.example.yikezhong.ui.shared.SharedPreferencesUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

//用户个人信息页面
public class UserNameActivity extends AppCompatActivity {
    @BindView(R.id.circleImageView)
    CircleImageView circleImageView;
    @BindView(R.id.userName)
    TextView userName;
    private int curren;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fabu);
        ButterKnife.bind(this);

        curren= (Integer) SharedPreferencesUtils.getParam(UserNameActivity.this,"position",0);
        if (curren== Configuration.UI_MODE_NIGHT_NO){
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    @OnClick({R.id.back, R.id.updata_name})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;

            case R.id.updata_name:  //编辑个人信息
                Intent intent = new Intent(UserNameActivity.this, UpLoadActivity.class);
                startActivityForResult(intent, 100);
                break;

            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        userName.setText(SharedPreferencesUtils.getString("nickname"));
        String icon = SharedPreferencesUtils.getString("icon");
        Glide.with(this).load(icon).into(circleImageView);
    }
}
