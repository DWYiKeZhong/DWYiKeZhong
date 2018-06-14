package com.example.yikezhong.ui.activity.videodetail;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.yikezhong.R;
import com.example.yikezhong.bean.VideoDetailBean;
import com.example.yikezhong.component.DaggerHttpComponent;
import com.example.yikezhong.module.HttpModule;
import com.example.yikezhong.ui.activity.videodetail.contract.VideoDetailContract;
import com.example.yikezhong.ui.activity.videodetail.presenter.VideoDetailPresenter;
import com.example.yikezhong.ui.base.BaseActivity;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import cn.jzvd.JZVideoPlayerStandard;

//视频详情页
public class VideoDetailActivity extends BaseActivity<VideoDetailPresenter> implements VideoDetailContract.View {

    private JZVideoPlayerStandard jzVideoPlayerStandard;
    private TextView title,time;
    private ImageView videodetailback,iv2,iv3,iv4;
    private int flag=1;
    private int flga=1;
    private String cover;
    private String workDesc;

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

    //分享回调接口
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat","platform"+platform);
            if(platform.name().equals("WEIXIN_FAVORITE")){
                Toast.makeText(VideoDetailActivity.this,platform + " 收藏成功啦",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(VideoDetailActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(VideoDetailActivity.this,platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if(t!=null){
                Log.d("throw","throw:"+t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(VideoDetailActivity.this,platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //6.0以上主动请求权限
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
        }

        jzVideoPlayerStandard = findViewById(R.id.videoplayer);
        title=findViewById(R.id.tittle);
        time=findViewById(R.id.time);
        videodetailback=findViewById(R.id.video_detail_back);
        iv2=findViewById(R.id.iv2);
        iv3=findViewById(R.id.iv3);
        iv4=findViewById(R.id.iv4);
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
        iv4.setOnClickListener(new View.OnClickListener() {    //分享
            @Override
            public void onClick(View v) {
                getDaiMianBan();
                //getNoDaiMianBan();
            }
        });
    }

    @Override
    public void getVideoDetailSuccess(final VideoDetailBean Bean) {
        cover = Bean.getData().getCover();
        workDesc = Bean.getData().getWorkDesc();

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
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        jzVideoPlayerStandard.releaseAllVideos();
        MobclickAgent.onPause(this);
    }

    //打开面板的使用方式与分享类似，只是一个设置单一的使用平台，一个设置一个平台list，一个最后调用的是share方法，一个调用open的方法。
    //带面板分享
    private void getDaiMianBan() {
        new ShareAction(VideoDetailActivity.this)
                .withText("视频详情：" + cover + workDesc)
                .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN)
                .setCallback(umShareListener)
                .withMedia(new UMImage(VideoDetailActivity.this, R.drawable.touxiang))
                .open();
    }

    //不带面板分享
    private void getNoDaiMianBan() {
        new ShareAction(VideoDetailActivity.this)
                .setPlatform(SHARE_MEDIA.QZONE)//传入平台
                .withText("视频详情：" + cover + workDesc)     //分享内容
                .setCallback(umShareListener)//回调监听器
                .withMedia(new UMImage(VideoDetailActivity.this, R.drawable.touxiang))
                .share();
    }

    //最后在Activity的onActivityResult中加入我们的回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
