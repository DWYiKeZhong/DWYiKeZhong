package com.example.yikezhong.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.yikezhong.R;
import com.example.yikezhong.ui.activity.login.LoginActivity;
import com.example.yikezhong.wxapi.MobActivity;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import cn.smssdk.SMSSDK;

//其他登录方式   Mob 友盟 第三方登录
public class OtherActivity extends MobActivity {
    @BindView(R.id.other_back)
    ImageView otherBack;
    @BindView(R.id.other_WinXin)
    ImageView otherWinXin;
    @BindView(R.id.other_QQ)
    ImageView otherQQ;
    @BindView(R.id.other_tv)
    TextView otherTv;
    public static OtherActivity instance = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        ButterKnife.bind(this);
        instance = this;

        otherBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        otherQQ.setOnClickListener(new View.OnClickListener() {     //第三方QQ登录
            @Override
            public void onClick(View view) {
                getQQ();     //Mob第三方QQ登录

            }
        });

        otherWinXin.setOnClickListener(new View.OnClickListener() {//第三方微信登录
            @Override
            public void onClick(View view) {
                getWeiXin();  //Mob第三方微信登录
                System.out.println( "微信 ");
            }
        });

        otherTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {                      //其他登录方式登录
                startActivity(new Intent(OtherActivity.this, LoginActivity.class));
            }
        });
    }

    //Mob第三方登录QQ
    public void getQQ(){

        Platform plat = ShareSDK.getPlatform(QQ.NAME);
        plat.removeAccount(true); //移除授权状态和本地缓存，下次授权会重新授权
        plat.SSOSetting(false); //SSO授权，传false默认是客户端授权，没有客户端授权或者不支持客户端授权会跳web授权
        plat.setPlatformActionListener(new PlatformActionListener() {//授权回调监听，监听oncomplete，onerror，oncancel三种状态
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                Iterator iterator = hashMap.entrySet().iterator();
                while (iterator.hasNext()){
                    Map.Entry next = (Map.Entry) iterator.next();
                    Object key = next.getKey();
                    Object value = next.getValue();

                    Toast.makeText(OtherActivity.this, "QQ登录成功", Toast.LENGTH_SHORT).show();
                    System.out.println( "QQ onComplete: "+key+"  "+value);

                }
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Toast.makeText(OtherActivity.this, "QQ登录失败", Toast.LENGTH_SHORT).show();

                System.out.println( "QQ onError: "+platform.getName()+" "+platform.getDb()+"  "+"  "+platform.getId()+"  "+platform.getPlatformActionListener()+" "+platform.getVersion());
            }

            @Override
            public void onCancel(Platform platform, int i) {
                Toast.makeText(OtherActivity.this, "QQ登录取消", Toast.LENGTH_SHORT).show();

                System.out.println(  "QQ onCancel: "+platform.getName());
            }
        });
        if (plat.isClientValid()) {
            //todo 判断是否存在授权凭条的客户端，true是有客户端，false是无
        }
        if (plat.isAuthValid()) {
            //todo 判断是否已经存在授权状态，可以根据自己的登录逻辑设置
            Toast.makeText(this, "已经授权过了", Toast.LENGTH_SHORT).show();
            return;
        }
        //plat.authorize();    //要功能，不要数据
        plat.showUser(null);    //要数据不要功能，主要体现在不会重复出现授权界面
    }

    //Mob第三方微信登录
    public void getWeiXin(){

        Platform plat = ShareSDK.getPlatform(Wechat.NAME);
        plat.removeAccount(true); //移除授权状态和本地缓存，下次授权会重新授权
        plat.SSOSetting(false); //SSO授权，传false默认是客户端授权，没有客户端授权或者不支持客户端授权会跳web授权
        plat.setPlatformActionListener(new PlatformActionListener() {//授权回调监听，监听oncomplete，onerror，oncancel三种状态
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                Iterator iterator = hashMap.entrySet().iterator();
                while (iterator.hasNext()){
                    Map.Entry next = (Map.Entry) iterator.next();
                    Object key = next.getKey();
                    Object value = next.getValue();

                    Toast.makeText(OtherActivity.this, "微信登录成功", Toast.LENGTH_SHORT).show();

                    System.out.println( "返回数据next: "+next.toString());
                    System.out.println( "微信onComplete: "+key+"  "+value);

                }
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Toast.makeText(OtherActivity.this, "微信登录失败", Toast.LENGTH_SHORT).show();

                System.out.println( "微信onError: "+platform.getName()+" "+platform.getDb()+"  "+"  "+platform.getId()+"  "+platform.getPlatformActionListener()+" "+platform.getVersion());
            }

            @Override
            public void onCancel(Platform platform, int i) {
                Toast.makeText(OtherActivity.this, "微信登录取消", Toast.LENGTH_SHORT).show();

                System.out.println( "微信onCancel: "+platform.getName());
            }
        });
        if (plat.isClientValid()) {
            //todo 判断是否存在授权凭条的客户端，true是有客户端，false是无
        }
        if (plat.isAuthValid()) {
            //todo 判断是否已经存在授权状态，可以根据自己的登录逻辑设置
            Toast.makeText(this, "已经授权过了", Toast.LENGTH_SHORT).show();
            return;
        }
        //plat.authorize();    //要功能，不要数据
        plat.showUser(null);    //要数据不要功能，主要体现在不会重复出现授权界面
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Mob用完回调要注销掉，否则可能会出现内存泄露
        SMSSDK.unregisterAllEventHandler();
    };

}
