package com.example.yikezhong.component;

import com.example.yikezhong.module.HttpModule;

import dagger.Component;

/**
 * Created   by   Dewey .
 */
@Component(modules = HttpModule.class)
public interface HttpComponent {
    void inject();
}
