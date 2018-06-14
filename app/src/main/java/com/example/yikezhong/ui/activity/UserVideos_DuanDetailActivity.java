package com.example.yikezhong.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.yikezhong.R;
import com.example.yikezhong.bean.GetUserVideos_DuanDetailBean;
import com.example.yikezhong.component.DaggerHttpComponent;
import com.example.yikezhong.module.HttpModule;
import com.example.yikezhong.ui.activity.adapter.GetUserVideos_DuanDetailAdapter;
import com.example.yikezhong.ui.activity.contract.GetUserVideos_DuanDetailContract;
import com.example.yikezhong.ui.activity.presenter.GetUserVideos_DuanDetailPresenter;
import com.example.yikezhong.ui.base.BaseActivity;
import com.example.yikezhong.ui.shared.SharedPreferencesUtils;
import com.example.yikezhong.ui.utils.DialogUtil;
 import com.facebook.drawee.view.SimpleDraweeView;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//对应ID用户详情作品集页面  也是段子详情页面
public class UserVideos_DuanDetailActivity extends BaseActivity<GetUserVideos_DuanDetailPresenter> implements GetUserVideos_DuanDetailContract.View {
    @BindView(R.id.zuopin)
    TextView zuopin;
    @BindView(R.id.btn_guanzhu_user)
    Button btnGuanzhuUser;
    @BindView(R.id.zanText)
    TextView zanText;
    @BindView(R.id.userXrecyclerView)
    RecyclerView userXrecyclerView;
    @BindView(R.id.headImage_User)
    SimpleDraweeView headImageUser;
    private Handler handler = new Handler();
    private GetUserVideos_DuanDetailAdapter adapter;
    int num = 17;
    int page = 1;
    public static UserVideos_DuanDetailActivity instans=null;
    private int curren;
    private String name;

    @Override
    public int getContentLayout() {
        return R.layout.activity_user;
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
                Toast.makeText(UserVideos_DuanDetailActivity.this,platform + " 收藏成功啦",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(UserVideos_DuanDetailActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(UserVideos_DuanDetailActivity.this,platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if(t!=null){
                Log.d("throw","throw:"+t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(UserVideos_DuanDetailActivity.this,platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        //6.0以上主动请求权限
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
        }
        instans=this;
        curren= (Integer) SharedPreferencesUtils.getParam(UserVideos_DuanDetailActivity.this,"position",0);
        if (curren== Configuration.UI_MODE_NIGHT_NO){
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        initView();
    }

    public void initView() {

        //获取传递的数据,在本页面设置显示
        Intent intent = getIntent();
        int uid = intent.getIntExtra("uid", 3881);
        name = intent.getStringExtra("name");
        String headImage = intent.getStringExtra("headImage");
        headImageUser.setImageURI(headImage);
        System.out.println("uid = " + uid+" name = "+ name +" headImage = "+headImage);

        //添加参数,设置布局管理器以及数据适配器
        List<GetUserVideos_DuanDetailBean.DataBean> list = new ArrayList<>();
        mPresenter.getUserVideos_DuanDetailP(Integer.toString(uid), Integer.toString(page));
        LinearLayoutManager manager = new LinearLayoutManager(UserVideos_DuanDetailActivity.this, LinearLayoutManager.VERTICAL, false);
        userXrecyclerView.setLayoutManager(manager);
        userXrecyclerView.addItemDecoration(new DividerItemDecoration(UserVideos_DuanDetailActivity.this, DividerItemDecoration.VERTICAL));
        adapter = new GetUserVideos_DuanDetailAdapter(UserVideos_DuanDetailActivity.this, name, headImage);
        userXrecyclerView.setAdapter(adapter);

        //网络加载慢时，提示
        DialogUtil.showProgressDialog(UserVideos_DuanDetailActivity.this, "提示", "正在加载......");
    }

    @Override
    public void getUserVideos_DuanDetailSuccess(GetUserVideos_DuanDetailBean getUserVideosBean) {
        if (adapter != null) {
            adapter.addData(getUserVideosBean.getData());
            zuopin.setText(getUserVideosBean.getData().size()+"");

            DialogUtil.hideProgressDialog();     //在此提示数据加载完毕，隐藏提示框
            System.out.println("getUserVideosBean数据不为空："+getUserVideosBean.getData().toString());
        }else {
            System.out.println("getUserVideosBean数据为空："+getUserVideosBean.getData().toString());
        }
    }

    @OnClick({R.id.back_user, R.id.share_user, R.id.talk_user , R.id.btn_guanzhu_user, R.id.zan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_user:   //返回上一步
                finish();
                break;

            case R.id.share_user:
                Toast.makeText(UserVideos_DuanDetailActivity.this, "分享", Toast.LENGTH_SHORT).show();
                getDaiMianBan();
                //getNoDaiMianBan();
                break;

            case R.id.talk_user:
                Toast.makeText(UserVideos_DuanDetailActivity.this, "评论", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_guanzhu_user:
                btnGuanzhuUser.setText("已关注");
                break;

            case R.id.zan:
                Toast.makeText(UserVideos_DuanDetailActivity.this, " 赞 + 1", Toast.LENGTH_SHORT).show();
                zanUser();
                break;

            default:
                break;
        }
    }

    //设置更新任务，更新赞的数量
    public void zanUser() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                // 为TextView控件赋值
                zanText.setText(Integer.toString(num));
                num++;
            }
        });
    }

    //打开面板的使用方式与分享类似，只是一个设置单一的使用平台，一个设置一个平台list，一个最后调用的是share方法，一个调用open的方法。
    //带面板分享
    private void getDaiMianBan() {
        new ShareAction(UserVideos_DuanDetailActivity.this)
                .withText("我发布的评论：" + name)
                .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN)
                .setCallback(umShareListener)
                .withMedia(new UMImage(UserVideos_DuanDetailActivity.this, R.drawable.touxiang))
                .open();
    }

    //不带面板分享
    private void getNoDaiMianBan() {
        new ShareAction(UserVideos_DuanDetailActivity.this)
                .setPlatform(SHARE_MEDIA.QZONE)//传入平台
                .withText("我发布的评论：" + name)     //分享内容
                .setCallback(umShareListener)//回调监听器
                .withMedia(new UMImage(UserVideos_DuanDetailActivity.this, R.drawable.touxiang))
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
