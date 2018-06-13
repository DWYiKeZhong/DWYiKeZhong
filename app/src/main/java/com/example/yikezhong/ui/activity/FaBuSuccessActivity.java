package com.example.yikezhong.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.example.yikezhong.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//发布成功页面，分享   https://www.jianshu.com/p/ccfa6bb0d17d
public class FaBuSuccessActivity extends AppCompatActivity {
    @BindView(R.id.goBack)
    LinearLayout goBack;
    @BindView(R.id.qqShare)
    ImageView qqShare;
    @BindView(R.id.vShare)
    ImageView vShare;
    @BindView(R.id.lookBtn)
    Button lookBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fa_bu_success);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.goBack, R.id.qqShare, R.id.vShare, R.id.lookBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.goBack:
                finish();
                break;

            case R.id.qqShare:
                Toast.makeText(FaBuSuccessActivity.this,"QQ分享",Toast.LENGTH_SHORT).show();
                break;

            case R.id.vShare:
                Toast.makeText(FaBuSuccessActivity.this,"微信分享",Toast.LENGTH_SHORT).show();
                break;

            case R.id.lookBtn:
                startActivity(new Intent(FaBuSuccessActivity.this,HomeActivity.class));
                finish();
                break;

            default:
                break;
        }
    }
}

