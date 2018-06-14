package com.example.yikezhong.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.example.yikezhong.R;
import com.example.yikezhong.wxapi.MobActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

//作品发布页面
public class FaBuActivity extends MobActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fabu);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.over, R.id.img_shipin, R.id.img_duanzi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.over:
                finish();
                break;

            case R.id.img_shipin:       //视频拍照
                startActivity(new Intent(FaBuActivity.this,TakePhotoActivity.class));
                break;

            case R.id.img_duanzi:       //发表文章段子
                startActivity(new Intent(FaBuActivity.this,FaBiaoDuznZiActivity.class));
                break;

            default:
                break;
        }
    }
}
