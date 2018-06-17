package com.example.yikezhong.ui.activity.setting;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import com.example.yikezhong.R;
import com.example.yikezhong.ui.shared.SharedPreferencesUtils;
import com.example.yikezhong.ui.utils.FileCacheUtils;
import java.io.File;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//设置页面：清除缓存，退出登录
public class SettingActivity extends AppCompatActivity {
    @BindView(R.id.setup_num)
    TextView setup_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        ButterKnife.bind(this);
        File outCachePath = SettingActivity.this.getExternalCacheDir();

        try {
            String outCacheSize = FileCacheUtils.getCacheSize(outCachePath);

            setup_num.setText(outCacheSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.main_menu,R.id.setup_clear,R.id.back_text, R.id.exit_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_menu:
                finish();
                break;

            case R.id.back_text:
                finish();
                break;

            case R.id.setup_clear:
                FileCacheUtils.cleanExternalCache(SettingActivity.this);
                //重新获取一次缓存大小，自处理M，byte
                initCacheSize();
                break;

            case R.id.exit_btn:
                SharedPreferencesUtils.clear(SettingActivity.this);
                finish();
                break;
        }
    }

    private void initCacheSize() {
        String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        File outCachePath = SettingActivity.this.getExternalCacheDir();
        File outFilePath = SettingActivity.this.getExternalFilesDir(Environment.DIRECTORY_ALARMS);

        try {
            String outCacheSize = FileCacheUtils.getCacheSize(outCachePath);
            String outFileSize = FileCacheUtils.getCacheSize(outFilePath);

            setup_num.setText(outCacheSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
