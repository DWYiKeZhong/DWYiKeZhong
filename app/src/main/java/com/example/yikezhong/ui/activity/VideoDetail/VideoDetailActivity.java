package com.example.yikezhong.ui.activity.VideoDetail;

import android.content.Intent;
import android.media.JetPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yikezhong.R;
import com.example.yikezhong.bean.VideoDetailBean;
import com.example.yikezhong.component.DaggerHttpComponent;
import com.example.yikezhong.module.HttpModule;
import com.example.yikezhong.ui.activity.VideoDetail.contract.VideoDetailContract;
import com.example.yikezhong.ui.activity.VideoDetail.presenter.VideoDetailPresenter;
import com.example.yikezhong.ui.base.BaseActivity;

import butterknife.BindView;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

public class VideoDetailActivity extends BaseActivity<VideoDetailPresenter> implements VideoDetailContract.View {

    private JZVideoPlayerStandard jzVideoPlayerStandard;
    private TextView title,time;
    private ImageView videodetailback;

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
        Intent intent=getIntent();
        String wid=intent.getStringExtra("wid");
        mPresenter.getVideoDetail("100",wid);
        videodetailback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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
