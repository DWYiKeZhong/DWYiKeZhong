package com.example.yikezhong.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.example.yikezhong.R;
import com.example.yikezhong.wxapi.MobActivity;
import com.example.yikezhong.wxapi.ShareUtil;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
   APPKEY  5b1fd470a40fa30c19000284
   发布成功页面，分享
   参考：file:///D:/知识集锦/第三方分享/友盟社会化分享Android文档%20_%20Android%20_%20社会化分享%20_%20开发者中心%20_%20友盟-专业的移动开发者服务平台%20_%20移动应用统计%20_%20Android统计%20_%20iPhone统计.html
 */
public class FaBuSuccessActivity extends MobActivity {

    //分享回调接口
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat","platform"+platform);
            if(platform.name().equals("WEIXIN_FAVORITE")){
                Toast.makeText(FaBuSuccessActivity.this,platform + " 收藏成功啦",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(FaBuSuccessActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(FaBuSuccessActivity.this,platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if(t!=null){
                Log.d("throw","throw:"+t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(FaBuSuccessActivity.this,platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };
    private String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fa_bu_success);
        ButterKnife.bind(this);

        //6.0以上主动请求权限
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
        }

        Intent intent = getIntent();
        content = intent.getStringExtra("content");
    }

    @OnClick({R.id.goBack, R.id.qqShare,R.id.qZoneShare,R.id.vFriendsShare, R.id.vShare, R.id.lookBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.goBack:  //返回上一步
                finish();
                break;

            case R.id.qqShare:  //QQ分享
                Toast.makeText(FaBuSuccessActivity.this,"QQ分享",Toast.LENGTH_SHORT).show();

                qqShare(view);
                //getDaiMianBan();

                break;

            case R.id.qZoneShare:  //QQ空间
                Toast.makeText(FaBuSuccessActivity.this,"QQ空间分享",Toast.LENGTH_SHORT).show();

                getNoDaiMianBan();

                break;

            case R.id.vFriendsShare:  //朋友圈
                Toast.makeText(FaBuSuccessActivity.this,"朋友圈分享",Toast.LENGTH_SHORT).show();

                //weichatShare(view);
                getDaiMianBan();

                break;

            case R.id.vShare:  //微信分享
                Toast.makeText(FaBuSuccessActivity.this,"微信分享",Toast.LENGTH_SHORT).show();

                weichatShare(view);
                //getDaiMianBan();

                break;

            case R.id.lookBtn: //主页面查看
                startActivity(new Intent(FaBuSuccessActivity.this,HomeActivity.class));
                finish();
                break;

            default:
                break;
        }
    }

    /**
     * 使用工具类分享到QQ,不带面板分享
     * @param view
     */
    public void qqShare(View view) {

        //分享一个链接...链接地址,,,标题,,,描述,,,图标的路径,,,本地的图标,,,要分享到的平台
        ShareUtil.shareWeb(FaBuSuccessActivity.this,"http://www.baidu.com","段子内容：" + content,"我发布的段子....","https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=1688773625,3145554638&fm=173&app=25&f=JPEG?w=640&h=640&s=E0941F9C4F1240D80B59A8CA030080B2",R.mipmap.ic_launcher_round, SHARE_MEDIA.QQ);

    }

    /**
     * 使用工具类分享到微信,不带面板分享
     * @param view
     */
    public void weichatShare(View view) {

        //分享一个链接...链接地址,,,标题,,,描述,,,图标的路径,,,本地的图标,,,要分享到的平台
        ShareUtil.shareWeb(FaBuSuccessActivity.this,"http://www.baidu.com","段子内容：" + content,"我发布的段子....","https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=1688773625,3145554638&fm=173&app=25&f=JPEG?w=640&h=640&s=E0941F9C4F1240D80B59A8CA030080B2",R.mipmap.ic_launcher_round, SHARE_MEDIA.WEIXIN);

    }

    //打开面板的使用方式与分享类似，只是一个设置单一的使用平台，一个设置一个平台list，一个最后调用的是share方法，一个调用open的方法。
    //带面板分享
    private void getDaiMianBan() {
        new ShareAction(FaBuSuccessActivity.this)
                .withText("我发布的段子：" + content)
                .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN)
                .setCallback(umShareListener)
                .withMedia(new UMImage(FaBuSuccessActivity.this, R.drawable.touxiang))
                .open();
    }

    //不带面板分享
    private void getNoDaiMianBan() {
        new ShareAction(FaBuSuccessActivity.this)
                .setPlatform(SHARE_MEDIA.QZONE)//传入平台
                .withText("我发布的段子：" + content)     //分享内容
                .setCallback(umShareListener)//回调监听器
                .withMedia(new UMImage(FaBuSuccessActivity.this, R.drawable.touxiang))
                .share();
    }

    //最后在Activity的onActivityResult中加入我们的回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
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

