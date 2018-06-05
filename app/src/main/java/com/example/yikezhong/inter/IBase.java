package com.example.yikezhong.inter;

import android.view.View;

public interface IBase {
    int getContentLayout();//返回布局内容

    void inject();         //注入的方法

    void initView(View view);//初始化视图
}
