package com.example.yikezhong.component;

import com.example.yikezhong.module.HttpModule;
import com.example.yikezhong.ui.tuijian_fragment.Tui_Guan_Fragment;
import com.example.yikezhong.ui.tuijian_fragment.Tui_Hot_Fragment;
import dagger.Component;

/**
 * Created   by   Dewey .
 */
@Component(modules = HttpModule.class)
public interface HttpComponent {
    void inject(Tui_Hot_Fragment tui_hot_fragment);
    void inject(Tui_Guan_Fragment tui_guan_fragment);
}
