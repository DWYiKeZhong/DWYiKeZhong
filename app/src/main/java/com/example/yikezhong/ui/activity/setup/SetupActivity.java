package com.example.yikezhong.ui.activity.setup;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.yikezhong.R;
import com.example.yikezhong.ui.utils.FileCacheUtils;
import java.io.File;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetupActivity extends AppCompatActivity {

                @BindView(R.id.setup_num)
                TextView setupNum;
                @BindView(R.id.setup_clear)
                RelativeLayout setupClear;

                @Override
                protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.activity_setup);
                    ButterKnife.bind(this);
                    File outCachePath = SetupActivity.this.getExternalCacheDir();

                    try {
                        String outCacheSize = FileCacheUtils.getCacheSize(outCachePath);

                        setupNum.setText(outCacheSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.setup_clear)
    public void onViewClicked() {
        FileCacheUtils.cleanExternalCache(SetupActivity.this);
        //重新获取一次缓存大小，自处理M，byte
        initCacheSize();
    }

    private void initCacheSize() {
        String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        File outCachePath = SetupActivity.this.getExternalCacheDir();
        File outFilePath = SetupActivity.this.getExternalFilesDir(Environment.DIRECTORY_ALARMS);

        try {
            String outCacheSize = FileCacheUtils.getCacheSize(outCachePath);
            String outFileSize = FileCacheUtils.getCacheSize(outFilePath);

            setupNum.setText(outCacheSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
