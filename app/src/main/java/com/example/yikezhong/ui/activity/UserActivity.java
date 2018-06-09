package com.example.yikezhong.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.yikezhong.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

//用户页面
public class UserActivity extends AppCompatActivity {
    @BindView(R.id.share_user)
    ImageView shareUser;
    @BindView(R.id.talk_user)
    ImageView talkUser;
    @BindView(R.id.headImage_User)
    CircleImageView headImageUser;
    @BindView(R.id.zuopin)
    TextView zuopin;
    @BindView(R.id.guanzhu_user)
    TextView guanzhuUser;
    @BindView(R.id.btn_guanzhu_user)
    Button btnGuanzhuUser;
    @BindView(R.id.zanText)
    TextView zanText;
    @BindView(R.id.zan)
    LinearLayout zan;
    int num = 17;
    private Handler handler =  new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back_user,R.id.share_user, R.id.talk_user, R.id.headImage_User, R.id.btn_guanzhu_user, R.id.zan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_user:
                finish();
                break;

            case R.id.share_user:
                Toast.makeText(UserActivity.this, "分享", Toast.LENGTH_SHORT).show();
                break;

            case R.id.talk_user:
                Toast.makeText(UserActivity.this, "评论", Toast.LENGTH_SHORT).show();
                break;

            case R.id.headImage_User:
                break;

            case R.id.btn_guanzhu_user:
                Toast.makeText(UserActivity.this, " 关注 + 1", Toast.LENGTH_SHORT).show();
                guanzhuUser.setText("667");
                break;

            case R.id.zan:
                Toast.makeText(UserActivity.this, " 赞 + 1", Toast.LENGTH_SHORT).show();
                zanUser();
                break;

            default:
                break;
        }
    }

    //设置更新任务，更新赞的数量
    public void zanUser(){
       handler.post(new Runnable() {
           @Override
           public void run() {
               // 为TextView控件赋值
               zanText.setText(Integer.toString(num));
               num++;
           }
       });
    }

}
