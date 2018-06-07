package com.example.yikezhong.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yikezhong.R;
import com.example.yikezhong.ui.activity.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OtherActivity extends AppCompatActivity {

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
        instance=this;
        otherBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        otherQQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        otherWinXin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        otherTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OtherActivity.this, LoginActivity.class));
            }
        });
    }
}
