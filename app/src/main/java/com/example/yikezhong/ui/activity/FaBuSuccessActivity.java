package com.example.yikezhong.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.example.yikezhong.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//发布成功页面
public class FaBuSuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fa_bu_success);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.goBack, R.id.qqShare, R.id.vShare, R.id.lookBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.goBack:
                finish();
                break;

            case R.id.qqShare:
                break;

            case R.id.vShare:
                break;

            case R.id.lookBtn:
                startActivity(new Intent(FaBuSuccessActivity.this,HomeActivity.class));
                finish();
                break;
        }
    }
}
