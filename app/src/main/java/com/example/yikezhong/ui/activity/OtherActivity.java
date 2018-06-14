package com.example.yikezhong.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.yikezhong.R;
import com.example.yikezhong.ui.activity.login.LoginActivity;
import com.example.yikezhong.wxapi.MobActivity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

//其他登录方式   Mob  第三方登录
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
                getQQ();
            }
        });

        otherWinXin.setOnClickListener(new View.OnClickListener() {//第三方微信登录
            @Override
            public void onClick(View view) {
                getWeiXin();
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

    //第三方登录QQ
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

                    System.out.println( "QQ onComplete: "+key+"  "+value);

                }
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                System.out.println( "QQ onError: "+platform.getName()+" "+platform.getDb()+"  "+"  "+platform.getId()+"  "+platform.getPlatformActionListener()+" "+platform.getVersion());
            }

            @Override
            public void onCancel(Platform platform, int i) {
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


    // 第三方微信登录
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
                    System.out.println( "微信onComplete: "+key+"  "+value);

                }
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                System.out.println( "微信onError: "+platform.getName()+" "+platform.getDb()+"  "+"  "+platform.getId()+"  "+platform.getPlatformActionListener()+" "+platform.getVersion());
            }

            @Override
            public void onCancel(Platform platform, int i) {
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


    /*
    * 短信验证  有视图的
    * */
    public void sendCode(Context context) {
        RegisterPage page = new RegisterPage();
        //如果使用我们的ui，没有申请模板编号的情况下需传null
        page.setTempCode(null);
        page.setRegisterCallback(new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // 处理成功的结果
                    HashMap<String, Object> phoneMap = (HashMap<String, Object>) data;
                    String country = (String) phoneMap.get("country"); // 国家代码，如“86”
                    String phone = (String) phoneMap.get("phone"); // 手机号码，如“13800138000”
                    // TODO 利用国家代码和手机号码进行后续的操作
                } else {
                    // TODO 处理错误的结果
                }
            }
        });
        page.show(context);
    }



    //短信验证   无视图的:  请求验证码，其中country表示国家代码，如“86”；phone表示手机号码，如“13800138000”
    public void sendCode(String country, String phone) {
        // 注册一个事件回调，用于处理发送验证码操作的结果
        SMSSDK.registerEventHandler(new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // TODO 处理成功得到验证码的结果
                    // 请注意，此时只是完成了发送验证码的请求，验证码短信还需要几秒钟之后才送达
                } else{
                    // TODO 处理错误的结果
                }

            }
        });
        // 触发操作
        SMSSDK.getVerificationCode(country, phone);
    }

    // 提交验证码，其中的code表示验证码，如“1357”
    public void submitCode(String country, String phone, String code) {
        // 注册一个事件回调，用于处理提交验证码操作的结果
        SMSSDK.registerEventHandler(new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // TODO 处理验证成功的结果
                } else{
                    // TODO 处理错误的结果
                }

            }
        });
        // 触发操作
        SMSSDK.submitVerificationCode(country, phone, code);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //用完回调要注销掉，否则可能会出现内存泄露
        SMSSDK.unregisterAllEventHandler();
    };

}
