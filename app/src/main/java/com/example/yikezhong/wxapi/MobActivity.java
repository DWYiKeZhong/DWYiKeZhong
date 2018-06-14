package com.example.yikezhong.wxapi;

import android.support.v7.app.AppCompatActivity;
import com.umeng.analytics.MobclickAgent;

/**
 * 问题: 遗漏了Mobclick.onResume函数调用
   解决方案：每个Activity的onResume中都必须调用MobclickAgent.onResume。
   ────────────────────────────────────────────────────────────────────────────────────────────────────────────────
   问题：遗漏了Mobclick.onPaused函数调用
   解决方案：每个Activity的onPaused中都必须调用MobclickAgent.onPaused。

   详见问题链接 https://developer.umeng.com/docs/66632/detail/66948?um_channel=sdk
 */
public class MobActivity extends AppCompatActivity{

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
