package com.example.yikezhong.ui.activity.videodetail;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yikezhong.R;
import com.example.yikezhong.bean.VideoDetailBean;
import com.example.yikezhong.component.DaggerHttpComponent;
import com.example.yikezhong.module.HttpModule;
import com.example.yikezhong.ui.activity.HomeActivity;
import com.example.yikezhong.ui.activity.videodetail.contract.VideoDetailContract;
import com.example.yikezhong.ui.activity.videodetail.presenter.VideoDetailPresenter;
import com.example.yikezhong.ui.base.BaseActivity;
import com.example.yikezhong.ui.shared.SharedPreferencesUtils;

import cn.jzvd.JZVideoPlayerStandard;

public class VideoDetailActivity extends BaseActivity<VideoDetailPresenter> implements VideoDetailContract.View {

    private JZVideoPlayerStandard jzVideoPlayerStandard;
    private TextView title,time;
    private ImageView videodetailback,iv2,iv3;
    private int flag=1;
    private int flga=1;
    private  int curren;
    @Override
    public int getContentLayout() {
        return R.layout.activity_video_detail;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        jzVideoPlayerStandard = findViewById(R.id.videoplayer);
        title=findViewById(R.id.tittle);
        time=findViewById(R.id.time);
        videodetailback=findViewById(R.id.video_detail_back);
        iv2=findViewById(R.id.iv2);
        iv3=findViewById(R.id.iv3);
        curren= (Integer) SharedPreferencesUtils.getParam(VideoDetailActivity.this,"position",0);
        if (curren== Configuration.UI_MODE_NIGHT_NO){
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        Intent intent=getIntent();
        String wid=intent.getStringExtra("wid");
        mPresenter.getVideoDetail("100",wid);
        videodetailback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag==1){
                    iv2.setImageDrawable(getResources().getDrawable(R.drawable.raw_1499933215));
                    flag=2;
                }else {
                    iv2.setImageDrawable(getResources().getDrawable(R.drawable.raw_1499933216));
                    flag=1;
                }
            }
        });
        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flga==1){
                    iv3.setImageDrawable(getResources().getDrawable(R.drawable.raw_14999332161));
                    flga=2;
                }else {
                    iv3.setImageDrawable(getResources().getDrawable(R.drawable.raw_1499933217));
                    flga=1;
                }
            }
        });
    }

    @Override
    public void getVideoDetailSuccess(VideoDetailBean Bean) {
        if (Bean.getData().getVideoUrl()!=null&&Bean.getData().getCover()!=null){
            jzVideoPlayerStandard.setUp(Bean.getData().getVideoUrl(), JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL);
            Glide.with(VideoDetailActivity.this).load(Bean.getData().getCover()).into( jzVideoPlayerStandard.thumbImageView);
        }
        if (Bean.getData().getWorkDesc()!=null){
            title.setText(Bean.getData().getWorkDesc());
        }
        time.setText(Bean.getData().getPlayNum()+"次播放   "+Bean.getData().getCreateTime());
    }

    @Override
    public void onBackPressed() {
        if (jzVideoPlayerStandard.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        jzVideoPlayerStandard.releaseAllVideos();
    }

}
